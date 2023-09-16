//import org.jetbrains.grammarkit.tasks.GenerateParserTask
import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.date
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.grammarkit.tasks.GenerateLexerTask
import org.jetbrains.grammarkit.tasks.GenerateParserTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    idea
    // Java support
    id("java")
    // Kotlin support
    alias(libs.plugins.kotlin.jvm)
    // Gradle Kover Plugin
    alias(libs.plugins.kotlin.kover)
    // Gradle IntelliJ Plugin
    alias(libs.plugins.intellij)
    // Gradle Grammar-Kit
    alias(libs.plugins.grammarkit)
    // Gradle Qodana Plugin
    alias(libs.plugins.qodana)
    // Gradle Changelog Plugin
    alias(libs.plugins.changelog)
}

group = properties("pluginGroup")
version = properties("pluginVersion")

sourceSets["main"].java.srcDirs("src/main/gen")

// Configure project's dependencies
repositories {
    maven("https://artifactory.bit-build.de/artifactory/public")
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    version.set(properties("pluginVersion"))
    header.set(provider { "[${version.get()}] - ${date("yyyy-MM-dd")}" })
    itemPrefix.set("*")
    groups.set(emptyList())
    repositoryUrl.set(properties("pluginRepositoryUrl"))
}

// Configure Gradle Qodana Plugin - read more: https://github.com/JetBrains/gradle-qodana-plugin
qodana {
    cachePath.set(projectDir.resolve(".qodana").canonicalPath)
    reportPath.set(projectDir.resolve("build/reports/inspections").canonicalPath)
    saveReport.set(true)
    showReport.set(System.getenv("QODANA_SHOW_REPORT")?.toBoolean() ?: false)
}

//kover.xmlReport {
//    onCheck.set(true)
//}

grammarKit {
    jflexRelease.set("1.7.0-1")
    grammarKitRelease.set("2021.1.2")
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(properties("javaVersion")))
    }
}

tasks {
    wrapper {
        gradleVersion = properties("gradleVersion")
    }

    // Generate manual, because of
    val generateZenScriptParser = withType<GenerateParserTask> {
        sourceFile.set(File("src/main/resources/net/eratiem/zenscriptsupport/language/ZenScript.bnf"))
        targetRoot.set("src/main/gen")
        pathToParser.set("/net/eratiem/zenscriptsupport/language/parser/ZenScriptParser")
        pathToPsiRoot.set("/net/eratiem/zenscriptsupport/language/psi")
        purgeOldFiles.set(false)
    }

    val generateZenScriptLexer = withType<GenerateLexerTask> {
        sourceFile.set(File("src/main/resources/net/eratiem/zenscriptsupport/language/ZenScript.flex"))
        targetDir.set("src/main/gen/net/eratiem/zenscriptsupport/language")
        targetClass.set("ZenScriptLexer")
        purgeOldFiles.set(true)
    }

    withType<KotlinCompile> {
        dependsOn(
            generateZenScriptParser,
            generateZenScriptLexer
        )
    }

    patchPluginXml {
        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))
        untilBuild.set(properties("pluginUntilBuild"))

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription.set(
            file("README.md").readText().lines().run {
                val start = "<!-- Plugin description -->"
                val end = "<!-- Plugin description end -->"

                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end))
            }.joinToString("\n").let { markdownToHTML(it) }
        )

        // Get the latest available change notes from the changelog file
        changeNotes.set(provider {
            with(changelog) {
                renderItem(
                    getOrNull(properties("pluginVersion")) ?: getLatest(),
                    Changelog.OutputType.HTML,
                )
            }
        })
    }

    // Configure UI tests plugin
    // Read more: https://github.com/JetBrains/intellij-ui-test-robot
    runIdeForUiTests {
        systemProperty("robot-server.port", "8082")
        systemProperty("ide.mac.message.dialogs.as.sheets", "false")
        systemProperty("jb.privacy.policy.text", "<!--999.999-->")
        systemProperty("jb.consents.confirmation.enabled", "false")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        // pluginVersion is based on the SemVer (https://semver.org) and supports pre-release labels, like 2.1.7-alpha.3
        // Specify pre-release label to publish the plugin in a custom Release Channel automatically. Read more:
        // https://plugins.jetbrains.com/docs/intellij/deployment.html#specifying-a-release-channel
        channels.set(listOf(properties("pluginVersion").split('-').getOrElse(1) { "default" }.split('.').first()))
    }
}

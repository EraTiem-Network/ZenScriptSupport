import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.05"

project {

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    maxRunningBuildsPerBranch = "*:1"

    params {
        param("env.pluginVerifierHomeDir", "~/.pluginVerifier")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Export Properties"
            scriptContent = """
                PROPERTIES="${'$'}(./gradlew properties --console=plain -q)"
                VERSION="${'$'}(echo "${'$'}PROPERTIES" | grep "^version:" | cut -f2- -d ' ')" 
                NAME="${'$'}(echo "${'$'}PROPERTIES" | grep "^pluginName:" | cut -f2- -d ' ')" 
                CHANGELOG="${'$'}(./gradlew getChangelog --unreleased --no-header --console=plain -q)"
                echo "${'$'}CHANGELOG"
                #CHANGELOG="${'$'}{CHANGELOG//'%'/'%25'}" 
                #CHANGELOG="${'$'}{CHANGELOG//${'$'}'\n'/'%0A'}" 
                #CHANGELOG="${'$'}{CHANGELOG//${'$'}'\r'/'%0D'}" 
                
                echo "##teamcity[setParameter name='env.VERSION' value='${'$'}VERSION']"
                echo "##teamcity[setParameter name='env.NAME' value='${'$'}NAME']"
                #echo "##teamcity[setParameter name='env.CHANGELOG' value='${'$'}CHANGELOG']"
                echo "##teamcity[setParameter name='env.pluginVerifierHomeDir' value='~/.pluginVerifier']"
                
                ./gradlew listProductsReleases # prepare list of IDEs for Plugin Verifier
                ls -lah ./build
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildDependencies", "Requires Artifactory Pro.")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
        }
        script {
            name = "Run Tests"
            scriptContent = """
                ./gradlew test
                echo "##teamcity[publishArtifacts './build/reports/tests']"
            """.trimIndent()
            formatStderrAsError = true
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        script {
            name = "Run Plugin Verification"
            scriptContent = """
                ./gradlew runPluginVerifier -Pplugin.verifier.home.dir=%env.pluginVerifierHomeDir%
                echo "##teamcity[publishArtifacts './build/reports/pluginVerifier']"
            """.trimIndent()
            formatStderrAsError = true
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }

    triggers {
        vcs {
        }
    }

    failureConditions {
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "FAILURE: Build failed with an exception."
            failureMessage = "Build failed with an exception"
            reverse = false
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = """BUILD FAILED in \d+s"""
            reverse = false
        }
    }

    features {
        perfmon {
        }
        commitStatusPublisher {
            vcsRootExtId = "${DslContext.settingsRoot.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = vcsRoot()
            }
        }
    }
})

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.script
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
                CHANGELOG="${'$'}{CHANGELOG//'%'/'%25'}" 
                CHANGELOG="${'$'}{CHANGELOG//${'$'}'\n'/'%0A'}" 
                CHANGELOG="${'$'}{CHANGELOG//${'$'}'\r'/'%0D'}" 
                
                echo "##teamcity[setParameter name='env.VERSION' value='${'$'}VERSION']"
                echo "##teamcity[setParameter name='env.NAME' value='${'$'}NAME']"
                echo "##teamcity[setParameter name='env.CHANGELOG' value='${'$'}CHANGELOG']"
                echo "##teamcity[setParameter name='env.pluginVerifierHomeDir' value='~/.pluginVerifier']"
                
                #./gradlew listProductsReleases # prepare list of IDEs for Plugin Verifier
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildDependencies", "Requires Artifactory Pro.")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
        }
        gradle {
            name = "Run Tests"
            tasks = "buildDependents"
            buildFile = "build.gradle.kts"
            incremental = true
            coverageEngine = idea {
                includeClasses = "net.eratiem.*"
            }
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
        gradle {
            name = "Liat Product Releases"
            tasks = "listProductsReleases"
            buildFile = "build.gradle.kts"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MegaChat"
include(":app")
include(":chat-list-api")
include(":chat-list-impl")
include(":chat-api")
include(":chat-impl")
include(":core-network-api")
include(":core-network-impl")
include(":core")
include(":registration-api")
include(":registration-impl")
include(":design-system")

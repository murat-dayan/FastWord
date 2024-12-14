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

rootProject.name = "Fast Word"
include(":app")
include(":core:common")
include(":core:ui")
include(":core:navigation")
include(":data")
include(":domain")
include(":feature:friends")
include(":feature:game")
include(":feature:leaderboard")
include(":feature:settings")
include(":feature:shop")
include(":feature:auth")
include(":feature:profile")

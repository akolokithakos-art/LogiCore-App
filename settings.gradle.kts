pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()

        // OPTIONAL but safe for MapLibre artifacts
        maven("https://maven.maplibre.org/releases")
    }
}

rootProject.name = "logicore"
include(":app")
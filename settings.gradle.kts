rootProject.name = "MidwestChampionshipSeries"

include(":androidApp")
include(":shared")
include(":desktopApp")

pluginManagement {
    includeBuild("gradle/build-logic")

    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

        maven { url = uri("https://jitpack.io") } // for moko-media android picker
    }
}

rootProject.name = "Midwest Championship Series"

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

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String
        val mokoResourcesVersion = extra["moko.resources.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)

        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)

        id("org.jetbrains.compose").version(composeVersion)

        id("dev.icerock.mobile.multiplatform-resources").version(mokoResourcesVersion)
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

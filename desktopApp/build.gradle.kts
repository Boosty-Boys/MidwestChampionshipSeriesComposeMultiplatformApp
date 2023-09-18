import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))

                implementation(libs.koin)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.1")
            }
        }
    }
}
compose.desktop {
    application {
        mainClass = "app.mcs.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "app.mcs"
            packageVersion = "1.0.0"
        }
    }
}
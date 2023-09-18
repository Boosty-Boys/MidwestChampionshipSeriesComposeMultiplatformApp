plugins {
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

spotless {
    kotlin {
        target("src/**/*.kt")
        ktlint(libs.versions.ktlint.get())
    }

    kotlinGradle {
        target("*.kts")
        ktlint(libs.versions.ktlint.get())
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("root") {
            id = "com.boostyboys.mcs.root"
            implementationClass = "com.boostyboys.mcs.gradle.RootConventionPlugin"
        }

        register("precommit") {
            id = "com.boostyboys.mcs.precommit"
            implementationClass = "com.boostyboys.mcs.gradle.PreCommitPlugin"
        }
    }
}

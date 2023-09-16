plugins {
    `kotlin-dsl`
    alias(libs.plugins.spotless)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
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
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("root") {
            id = "app.mcs.root"
            implementationClass = "app.mcs.gradle.RootConventionPlugin"
        }

        register("precommit") {
            id = "app.mcs.precommit"
            implementationClass = "app.mcs.gradle.PreCommitPlugin"
        }
    }
}

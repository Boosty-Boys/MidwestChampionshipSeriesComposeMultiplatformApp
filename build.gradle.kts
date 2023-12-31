plugins {
    id("com.boostyboys.mcs.root")

    kotlin("multiplatform").version(libs.versions.kotlin).apply(false)
    id("com.android.application").version(libs.versions.agp).apply(false)
    id("com.android.library").version(libs.versions.agp).apply(false)
    id("org.jetbrains.compose").version(libs.versions.compose).apply(false)
    // re-enable after kotlin 1.9 support https://github.com/icerockdev/moko-resources/issues/535
    // id("dev.icerock.mobile.multiplatform-resources").version(libs.versions.mokoResources).apply(false)
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    id("io.gitlab.arturbosch.detekt").version(libs.versions.detektGradlePlugin)
}

subprojects {
    apply(from = "$rootDir/buildscripts/detekt.gradle")
}

tasks {
    /**
     * The \detektAll tasks enables parallel usage for detekt so if this project
     * expands to multi module support, detekt can continue to run quickly.
     *
     * https://proandroiddev.com/how-to-use-detekt-in-a-multi-module-android-project-6781937fbef2
     */
    val detektAll by registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        baseline.set(file("$rootDir/config/detekt/baseline.xml"))
        buildUponDefaultConfig = false
    }

    val detektGenerateBaseline by registering(io.gitlab.arturbosch.detekt.DetektCreateBaselineTask::class) {
        description = "Custom DETEKT build to build baseline for all modules"
        baseline.set(file("$rootDir/config/detekt/baseline.xml"))
        setSource(files(projectDir))
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**", "**/build/**")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
    compilerOptions {
        allWarningsAsErrors.set(true)

        if (project.hasProperty("enableComposeCompilerReports")) {
            freeCompilerArgs.addAll(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                    project.buildDir.absolutePath + "/compose_metrics",
            )
            freeCompilerArgs.addAll(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                    project.buildDir.absolutePath + "/compose_metrics",
            )
        }
    }
}

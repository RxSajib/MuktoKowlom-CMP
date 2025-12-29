plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    kotlin("plugin.serialization") version "2.3.0"
}

kotlin {
    jvm()
    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.aliad.domain"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach{ iosTarget ->
        iosTarget.binaries.framework {
            baseName = "presentation"
            isStatic = true
        }
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val jvmMain by getting   // ← Add JVM source set
        val iosMain by creating {
            dependsOn(commonMain)
        }
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here
                implementation(libs.kotlinx.coroutines.core)

                implementation(libs.koin.core)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.kotlin.serialization.json)

                //paging3 dependency
                implementation("androidx.paging:paging-common:3.3.6")
                //paging3 dependency
            }
        }


    }

}
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.ajijul.elnaz"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ajijul.elnaz"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
     kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    buildFeatures {
        compose = true
    }
    dynamicFeatures += setOf(
        ":feature_barcode",
        ":feature_discount",
        ":feature_inventory",
        ":feature_products",
        ":feature_product_management",
        ":feature_checkout",
        ":feature_order",
        ":feature_category",
        ":feature_customer",
        ":feature_supplier",
        ":feature_main_inventory",
        ":feature_sample"
    )
}

configurations.all {
    // Remove the older Firebase protolite runtime globally
    exclude(group = "com.google.firebase", module = "protolite-well-known-types")
    // Force Gradle to use only the modern protobuf runtime
    resolutionStrategy {
        force(libs.google.protobuf)
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":auth"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":di"))
    implementation(project(":logger"))
    implementation(project(":extension"))
    implementation(project(":module_contracts"))
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.hilt.navigation)
    implementation(libs.hilt.android)
    implementation(libs.androidx.startup.runtime)
    kapt(libs.hilt.compiler)

    //FireBase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    implementation(libs.accompanist.permissions)
    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
//    androidTestImplementation(libs.androidx.test.junit)
//    androidTestImplementation(libs.androidx.test.core)
}
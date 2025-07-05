plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.ajijul.elnaz"
    compileSdk = 35

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    dynamicFeatures += setOf(":feature-barcode", ":feature-additem")
}

dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.hilt.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.firebase.firestore)
    implementation(libs.accompanist.permissions)
    implementation(libs.kotlinx.coroutines)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
//    androidTestImplementation(libs.androidx.test.junit)
//    androidTestImplementation(libs.androidx.test.core)
}
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.ajijul.elnaz.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}
protobuf {
    protoc {
        artifact = libs.google.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                // For Android we need lite runtime (smaller + faster)
                create("java") {
                    option("lite")
                }
            }
        }
    }
}
dependencies {

    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":logger"))
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    //FireBase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    //Firebase End

    implementation(libs.kotlinx.coroutines)
    implementation(libs.hilt.android)
    implementation(libs.datastore)
    implementation(libs.google.protobuf)
    kapt(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
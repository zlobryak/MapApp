import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize") //TODO описать зачем
    id("com.google.dagger.hilt.android") //TODO описать зачем
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "ru.netology.nmedia"
    compileSdk = 37

    defaultConfig {
        applicationId = "ru.netology.nmedia"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        val properties = Properties()
        if (rootProject.file("local.properties").exists()) {
            properties.load(rootProject.file("local.properties").inputStream())
        }

        val mapkitApiKey = properties.getProperty("MAPS_API_KEY", "")
        buildConfigField("String", "MAPS_API_KEY", "\"$mapkitApiKey\"")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity)
    implementation(libs.material)
    implementation(libs.maps.mobile)
    implementation(libs.maps.mobile.v4391lite)
    implementation(libs.androidx.room) // Room (для базы данных)
    ksp(libs.androidx.room.compiler) //Для компиляции базы данных

    implementation(libs.hilt.android) // Hilt (для внедрения зависимостей)
    ksp(libs.hilt.android.compiler) // Hilt (для внедрения зависимостей)
    implementation(libs.androidx.lifecycle.livedata.ktx) // Coroutines (для работы с асинхронностью)
    implementation(libs.androidx.fragment) // Для viewModel в рагментах

}
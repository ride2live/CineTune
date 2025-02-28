import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin") // 🔹 Добавляем Hilt-плагин явно
}

android {
    namespace = "com.nolwendroid.cinetune"
    compileSdk = 35
    buildFeatures {
        buildConfig = true //  BuildConfig support
    }
    defaultConfig {
        buildConfigField(
            "String",
            "TMDB_API_KEY",
            "\"${getApiKey("TMDB_API_KEY")}\""
        ) // ✅ Добавили кавычки
        buildConfigField(
            "String",
            "LASTFM_API_KEY",
            "\"${getApiKey("LASTFM_API_KEY")}\""
        ) // ✅ Добавили кавычки
        buildConfigField(
            "String",
            "LASTFM_API_SECRET_KEY",
            "\"${getApiKey("LASTFM_API_KEY_SECRET")}\""
        ) // ✅ Добавили кавычки
        applicationId = "com.nolwendroid.cinetune"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =
            libs.versions.composeCompiler.get() // ✅ Используем версию из TOML
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//Get api keys safely (no exposing in repository)
fun getApiKey(propertyName: String): String {
    val propertiesFile = rootProject.file("local.properties")
    if (!propertiesFile.exists()) {
        throw GradleException("You need keys in local/properties")
    }
    val properties = Properties()
    properties.load(propertiesFile.inputStream())
    return properties.getProperty(propertyName, "") // ✅ Берём ключ из local.properties
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    implementation(project(":core"))
    implementation(project(":feature_splash"))
    implementation(project(":feature_movie"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
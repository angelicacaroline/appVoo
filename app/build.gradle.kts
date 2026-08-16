import java.util.Properties

plugins {
    // Mantemos APENAS o plugin do Android. O Gradle já carrega o Kotlin implicitamente por baixo
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.gps.appvoos"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gps.appvoos"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Carrega o arquivo local.properties de forma segura para capturar o token
        val localProperties = Properties().apply {
            val file = rootProject.file("local.properties")
            if (file.exists()) {
                load(file.inputStream())
            }
        }

        val apiKey = localProperties.getProperty("RAPIDAPI_KEY") ?: ""
        buildConfigField("String", "RAPIDAPI_KEY", "\"$apiKey\"")
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

    // Configuração estável da máquina virtual Java compartilhada com o Kotlin
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
        buildConfig = true // Habilita a geração automática da classe invisível BuildConfig
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Instanciação estável do cliente OkHttp 5
    implementation("com.squareup.okhttp3:okhttp:5.4.0")

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinAndroidKsp)
    kotlin("plugin.serialization")
}

android {
    namespace = "com.muratdayan.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        val keyStoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keyStoreFile.inputStream())

        val supabaseUrl = properties.getProperty("SUPABASE_URL")?:""
        val supabaseKey = properties.getProperty("SUPABASE_KEY")?:""

        buildConfigField(
            type = "String",
            name = "SUPABASE_URL",
            value = supabaseUrl
        )

        buildConfigField(
            type = "String",
            name = "SUPABASE_KEY",
            value = supabaseKey
        )

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
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (platform (libs.supabase.bom))
    implementation (libs.realtime.kt)
    implementation (libs.postgrest.kt)
    implementation (libs.ktor.client.android)
    implementation (libs.kotlinx.serialization.json)


    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.nikhil.alertdialogprac"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nikhil.alertdialogprac"
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
    kotlinOptions{
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // Room components
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.play.services.location)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    androidTestImplementation("androidx.room:room-testing:2.6.1")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Kotlin components
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("androidx.media3:media3-exoplayer:1.3.1")
    implementation ("androidx.media3:media3-exoplayer-dash:1.3.1")
    implementation ("androidx.media3:media3-ui:1.3.1")

    //supabase storage
    implementation(platform("io.github.jan-tennert.supabase:bom:3.0.2"))
    implementation ("io.github.jan-tennert.supabase:storage-kt:3.0.2")
    //glide
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")
   


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
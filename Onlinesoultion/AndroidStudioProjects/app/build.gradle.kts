plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
    //id("androidx.navigation.safeargs.kotlin")
   // id("dagger.hilt.android.plugin")
   // id("com.google.gms.google-services")

}

android {
    namespace = "com.example.onlinesolution"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.onlinesolution"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    //added this
    buildFeatures{

        viewBinding = true
    }
}

dependencies {

    implementation(libs.core)
    testImplementation ("org.robolectric:robolectric:4.7")

    implementation("junit:junit:4.12")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.junit.ktx)
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")

    implementation ("androidx.constraintlayout:constraintlayout:2.1.0")

    // Recycler View (including List adapter)
    implementation("androidx.recyclerview:recyclerview:1.1.0")

// Material Library (for Fab and other material stuff)
    implementation("com.google.android.material:material:1.1.0")

// ViewModel & Livedata
    val lifecycleVersion = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycleVersion")

// Navigation Component
    val navVersion = "2.3.0"
    implementation("androidx.navigation:navigation-fragment:$navVersion")
    implementation("androidx.navigation:navigation-ui:$navVersion")


    implementation("com.github.bumptech.glide:glide:4.12.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation(libs.firebase.database.ktx)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("de.hdodenhof:circleimageview:3.1.0")

}
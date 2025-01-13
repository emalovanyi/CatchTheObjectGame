plugins {
    id("com.android.application") version "8.1.4"
    id("org.jetbrains.kotlin.android") version "1.9.0"
    id("org.jetbrains.kotlin.kapt") version "1.9.0" // ВАЖЛИВО: "org.jetbrains.kotlin.kapt"
}

android {
    namespace = "com.example.catchtheobjectgame"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.catchtheobjectgame"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Локальні юніт-тести (test)
    testImplementation("junit:junit:4.13.2")

    // Інструментовані тести (androidTest)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Якщо ви хочете використовувати AndroidJUnit4:
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
}

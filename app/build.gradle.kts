plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Якщо треба використовувати корутинні DAO-методи: id("org.jetbrains.kotlin.kapt")
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
    // Android UI
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // **RecyclerView** (щоб працювали 'recyclerview' та LinearLayoutManager)
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    // **Room** (без корутин; якщо потрібні корутини, див. нижче)
    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    // або якщо у вас Kotlin + kapt:
    // kapt("androidx.room:room-compiler:2.5.2")

    // (Якщо хочете корутинні DAO):
    // implementation("androidx.room:room-ktx:2.5.2")

    // Coroutines (для асинхронних викликів)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Тести (опційно)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

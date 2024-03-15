plugins {
    id("com.android.application")
}

android {
    namespace = "com.websarva.wings.android.applicationforvisualizingbudgets"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.websarva.wings.android.applicationforvisualizingbudgets"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.android.material:material:1.11.0")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.7.0")
    //Room
    implementation("androidx.room:room-runtime:2.6.1")//Room本体のライブラリ
    annotationProcessor("androidx.room:room-compiler:2.6.1")//Room本体のライブラリ
    implementation ("androidx.room:room-guava:2.6.1")  // 非同期処理用、Guavaライブラリの利用を設定
    implementation ("com.google.guava:guava:31.1-android")  // 非同期処理用、RoomとGuavaを連携

}
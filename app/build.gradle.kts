plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.assignment2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.assignment2"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
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
    implementation(libs.androidx.camera.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    val camera_version = "1.4.1"
    implementation("androidx.camera:camera-camera2:$camera_version")
    implementation("androidx.camera:camera-lifecycle:$camera_version")
    implementation("androidx.camera:camera-view:$camera_version")

    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    val nav_version = "2.8.6"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("io.coil-kt:coil-compose:1.4.0")

    val espressoVersion = "3.6.1"
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")

    // Core library
    val androidXTestVersion = "1.6.1"
    androidTestImplementation("androidx.test:core:$androidXTestVersion")

    // AndroidJUnitRunner and JUnit Rules
    val testRunnerVersion = "1.6.2"
    val testRulesVersion = "1.6.1"
    androidTestImplementation("androidx.test:runner:$testRunnerVersion")
    androidTestImplementation("androidx.test:rules:$testRulesVersion")

    // Assertions
    val testJunitVersion = "1.2.1"
    val truthVersion = "1.6.0"
    androidTestImplementation("androidx.test.ext:junit:$testJunitVersion")
    androidTestImplementation("androidx.test.ext:truth:$truthVersion")

    // Espresso dependencies
    androidTestImplementation( "androidx.test.espresso:espresso-core:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-contrib:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-intents:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-accessibility:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-web:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso.idling:idling-concurrent:$espressoVersion")

    // The following Espresso dependency can be either "implementation",
    // or "androidTestImplementation", depending on whether you want the
    // dependency to appear on your APK"s compile classpath or the test APK
    // classpath.
    androidTestImplementation( "androidx.test.espresso:espresso-idling-resource:$espressoVersion")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0")
    testImplementation(kotlin("test"))

}
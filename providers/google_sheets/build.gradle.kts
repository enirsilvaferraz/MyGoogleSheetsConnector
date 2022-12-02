/**
 * The first section in the build configuration applies the Android plugin for
 * Gradle to this build and makes the android block available to specify
 * Android-specific build options.
 */

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

/**
 * The android block is where you configure all your Android-specific
 * build options.
 */

@Suppress("UnstableApiUsage")
android {

    /**
     * The app's namespace. Used primarily to access app resources.
     */

    namespace = "com.eferraz.googlesheets"

    /**
     * compileSdkVersion specifies the Android API level Gradle should use to
     * compile your app. This means your app can use the API features included in
     * this API level and lower.
     */

    compileSdk = 33

    /**
     * The defaultConfig block encapsulates default settings and entries for all
     * build variants, and can override some attributes in main/AndroidManifest.xml
     * dynamically from the build system. You can configure product flavors to override
     * these values for different versions of your app.
     */

    defaultConfig {

        // Defines the minimum API level required to run the app.
        minSdk = 26

        // Specifies the API level used to test the app.
        targetSdk = 33
    }

    /**
     * The buildTypes block is where you can configure multiple build types.
     * By default, the build system defines two build types: debug and release. The
     * debug build type is not explicitly shown in the default build configuration,
     * but it includes debugging tools and is signed with the debug key. The release
     * build type applies Proguard settings and is not signed by default.
     */

    buildTypes {

        /**
         * By default, Android Studio configures the release build type to enable code
         * shrinking, using minifyEnabled, and specifies the default Proguard rules file.
         */

        getByName("release") {
            isMinifyEnabled = false // Enables code shrinking for the release build type.
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

/**
 * The dependencies block in the module-level build configuration file
 * specifies dependencies required to build only the module itself.
 * To learn more, go to Add build dependencies.
 */

dependencies {

    // Android
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    //implementation "androidx.activity:activity-compose:1.6.0"

    // Unit Tests
    testImplementation("junit:junit:4.13.2")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-compiler:2.42")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    testImplementation("com.google.dagger:hilt-android-testing:2.42")
    kaptTest("com.google.dagger:hilt-compiler:2.42")

    // Google Sheets
    implementation("com.google.apis:google-api-services-sheets:v4-rev20210629-1.32.1")
    implementation("com.google.android.gms:play-services-auth:20.3.0")
    implementation("com.google.api-client:google-api-client-android:1.35.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:30.2.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-auth-ktx")
}
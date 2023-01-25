/**
 * The first section in the build configuration applies the Android plugin for
 * Gradle to this build and makes the android block available to specify
 * Android-specific build options.
 */

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
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

    androidComponents.onVariants { variant ->
        val name = variant.name
        sourceSets {
            getByName(name).kotlin.srcDir("${buildDir.absolutePath}/generated/ksp/${name}/kotlin")
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
    //implementation("androidx.core:core-ktx:1.9.0")
    //implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    //implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    //implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    //implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1") // Optional - Integration with ViewModels
    //implementation "androidx.activity:activity-compose:1.6.0"

    // Unit Tests
    testImplementation("junit:junit:4.13.2")

    // Google Sheets
    implementation("com.google.apis:google-api-services-sheets:v4-rev20210629-1.32.1")
    implementation("com.google.android.gms:play-services-auth:20.3.0")
    implementation("com.google.api-client:google-api-client-android:1.35.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-auth-ktx")

    // Koin
    val koin_version = "3.3.2"
    val koin_android_version = "3.3.2"
    val koin_android_compose_version = "3.4.1"
    val koin_ksp_version = "1.1.0"

    implementation("io.insert-koin:koin-core:$koin_version")                              // Koin Core features
    testImplementation("io.insert-koin:koin-test:$koin_version")                          // Koin Test features
    testImplementation("io.insert-koin:koin-test-junit4:$koin_version")                   // Koin for JUnit 4
    testImplementation("io.insert-koin:koin-test-junit5:$koin_version")                   // Koin for JUnit 5
    implementation("io.insert-koin:koin-android:$koin_android_version")                   // Koin main features for Android
    implementation("io.insert-koin:koin-android-compat:$koin_android_version")            // Java Compatibility
    implementation("io.insert-koin:koin-androidx-workmanager:$koin_android_version")      // Jetpack WorkManager
    implementation("io.insert-koin:koin-androidx-navigation:$koin_android_version")       // Navigation Graph
    implementation("io.insert-koin:koin-androidx-compose:$koin_android_compose_version")  // Jetpack Compose
    implementation ("io.insert-koin:koin-annotations:$koin_ksp_version")                  // Koin Annotations
    ksp("io.insert-koin:koin-ksp-compiler:$koin_ksp_version")
}
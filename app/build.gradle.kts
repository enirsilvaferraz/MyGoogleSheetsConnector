/**
 * The first section in the build configuration applies the Android plugin for
 * Gradle to this build and makes the android block available to specify
 * Android-specific build options.
 */

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

/**
 * The android block is where you configure all your Android-specific
 * build options.
 */

@Suppress("UnstableApiUsage") android {

    /**
     * The app's namespace. Used primarily to access app resources.
     */

    namespace = "com.eferraz.mygooglesheetsconnector"

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

        // Uniquely identifies the package for publishing.
        applicationId = "com.eferraz.mygooglesheetsconnector"

        // Defines the minimum API level required to run the app.
        minSdk = 29

        // Specifies the API level used to test the app.
        targetSdk = 33

        // Defines the version number of your app.
        versionCode = 1

        // Defines a user-friendly version name for your app.
        versionName = "1.0"
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

        getByName("debug") {
            buildConfigField("String", "SHEET_KEY", "\"1ox8gMA-sD-TYEVuyZM_hODoZKfJqIRi_cZdEHNL_S8c\"")
        }

        getByName("release") {
            isMinifyEnabled = true // Enables code shrinking for the release build type.
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "SHEET_KEY", "\"1qC_sOAMcOHNNZpKDt4Qh-OETSpQ8GUHrLr5Op5f5Vbw\"")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0-alpha02"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
        }
    }

    hilt {
        enableAggregatingTask = true
    }

    applicationVariants.all { variant ->

        // Encapsulates configurations for the main source set.
        sourceSets.getByName("main") {
            // Changes the directory for Java sources. The default directory is
            // 'src/main/java'.
            java.setSrcDirs(listOf("build/generated/ksp/${variant.name}/kotlin"))
        }

        true
    }
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas")
        arg("room.incremental", "true")
        arg("room.expandProjection", "true")
    }
}


/**
 * The dependencies block in the module-level build configuration file
 * specifies dependencies required to build only the module itself.
 * To learn more, go to Add build dependencies.
 */

dependencies {

    implementation(project(":providers:google_sheets"))

    // Android
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1") // Optional - Integration with ViewModels

    // Compose
    implementation(platform("androidx.compose:compose-bom:2022.12.00"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-core") // Icons
    implementation("androidx.compose.material:material-icons-extended") // Optional - Add full set of material icons
    implementation("androidx.compose.material3:material3-window-size-class") // Optional - Add window size utils
    implementation("androidx.compose.ui:ui-tooling-preview") // Android Studio Preview support
    implementation("androidx.activity:activity-compose:1.6.1") // Optional - Integration with activities
    debugImplementation("androidx.compose.ui:ui-tooling") // Android Studio Preview support
    debugImplementation("androidx.compose.ui:ui-test-manifest") // UI Tests
    //androidTestImplementation("androidx.compose.ui:ui-test-junit4") // UI Tests

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // Status Bat
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    // Unit Tests
    testImplementation("junit:junit:4.13.2")

    // Hilt
    implementation("androidx.hilt:hilt-work:1.0.0")
    implementation("com.google.dagger:hilt-android:2.44.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    kapt("com.google.dagger:hilt-compiler:2.44.2")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    testImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    kaptTest("com.google.dagger:hilt-compiler:2.44.2")

    // WorkManager
    implementation("androidx.work:work-runtime-ktx:2.7.1")
    androidTestImplementation("androidx.work:work-testing:2.7.1")

    // ROOM
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    testImplementation("androidx.room:room-testing:2.4.3")

    // AssertK
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")

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
    ksp("io.insert-koin:koin-ksp-compiler:$koin_ksp_version")
}
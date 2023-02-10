/**
 * The first section in the build configuration applies the Android plugin for
 * Gradle to this build and makes the android block available to specify
 * Android-specific build options.
 */

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
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
        useLiveLiterals = true
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

    androidComponents.onVariants { variant ->
        val name = variant.name
        sourceSets {
            getByName(name).kotlin.srcDir("${buildDir.absolutePath}/generated/ksp/${name}/kotlin")
        }
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

    implementation(project(":libraries:google-client"))
    // Android
    implementation("androidx.work:work-runtime-ktx:2.7.1")

    implementation("com.google.android.gms:play-services-basement:18.1.0")
    implementation("com.google.apis:google-api-services-sheets:v4-rev20210629-1.32.1")
    implementation("com.google.android.gms:play-services-auth:20.3.0")
    implementation("com.google.api-client:google-api-client-android:1.35.0")

    // Tests
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.25")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-util")
    implementation("androidx.compose.ui:ui-text-google-fonts")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-ripple")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-tooling-data")
    debugImplementation("androidx.compose.ui:ui-tooling-preview")

    implementation("com.google.code.gson:gson:2.10.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.29.1-alpha")

    // ROOM
    implementation("androidx.room:room-runtime:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    testImplementation("androidx.room:room-testing:2.5.0")

    // Koin
    implementation("io.insert-koin:koin-core:3.3.2")                      // Koin Core features
    implementation("io.insert-koin:koin-android:3.3.2")                   // Koin main features for Android
    implementation("io.insert-koin:koin-android-compat:3.3.2")            // Java Compatibility
    implementation("io.insert-koin:koin-androidx-workmanager:3.3.2")      // Jetpack WorkManager
    implementation("io.insert-koin:koin-androidx-navigation:3.3.2")       // Navigation Graph
    implementation("io.insert-koin:koin-androidx-compose:3.4.1")          // Jetpack Compose
    implementation("io.insert-koin:koin-annotations:1.1.0")               // Koin Annotations
    ksp("io.insert-koin:koin-ksp-compiler:1.1.0")
    testImplementation("io.insert-koin:koin-test:3.3.2")                  // Koin Test features
    testImplementation("io.insert-koin:koin-test-junit4:3.3.2")           // Koin for JUnit 4
    testImplementation("io.insert-koin:koin-test-junit5:3.3.2")           // Koin for JUnit 5
}
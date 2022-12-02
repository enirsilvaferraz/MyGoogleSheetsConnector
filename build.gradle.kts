buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath ("com.google.gms:google-services:4.3.14")
    }
    repositories {
        google()
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {

    /**
     * You should use `apply false` in the top-level build.gradle file
     * to add a Gradle plugin as a build dependency, but not apply it to the
     * current (root) project. You should not use `apply false` in sub-projects.
     * For more information, see
     * Applying external plugins with same version to subprojects.
     */

    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("org.jetbrains.kotlin.kapt") version "1.6.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
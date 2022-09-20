package com.aej.buildsrc

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
}

object DefaultConfig {
    const val applicationId = "com.aej.efisheryandroidassessment"
    const val compileSdk = 32
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"
}

object Kotlin {}

object Android {

    private const val coreKtxVersion = "1.7.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
    const val appCompat = "androidx.appcompat:appcompat:1.5.1"
    const val material = "com.google.android.material:material:1.6.1"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0"

    object Jetpack {
        const val activityKtx = "androidx.activity:activity-ktx:1.2.3"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.4"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.0.0"
        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0-rc03"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
    }

    object TestLibs {
        const val junit = "junit:junit:4.13.2"
        const val extJunit = "androidx.test.ext:junit:1.1.3"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val mockitoKotlin = "com.nhaarman:mockito-kotlin:1.6.0"
        const val mockitoCore = "org.mockito:mockito-core:3.8.0"
        const val mockitoAndroid = "org.mockito:mockito-android:3.8.0"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava3:2.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:4.10.0"
        const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.10.0"
        const val gson = "com.google.code.gson:gson:2.8.5"
        const val converterGson = "com.squareup.retrofit2:converter-gson:2.6.1"
    }

    object ReactiveX {
        const val rxJava = "io.reactivex.rxjava3:rxjava:3.0.0"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:3.0.0"
    }

    object DI {
        private const val daggerVersion = "2.42"
        const val hiltAndroid= "com.google.dagger:hilt-android:$daggerVersion"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:$daggerVersion"
    }

}
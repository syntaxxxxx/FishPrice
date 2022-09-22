plugins {
    id(com.aej.buildsrc.Plugins.androidApplication)
    id(com.aej.buildsrc.Plugins.kotlinAndroid)
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    compileSdk = (com.aej.buildsrc.DefaultConfig.compileSdk)

    defaultConfig {
        applicationId = (com.aej.buildsrc.DefaultConfig.applicationId)
        minSdk = (com.aej.buildsrc.DefaultConfig.minSdk)
        targetSdk = (com.aej.buildsrc.DefaultConfig.targetSdk)
        versionCode = (com.aej.buildsrc.DefaultConfig.versionCode)
        versionName = (com.aej.buildsrc.DefaultConfig.versionName)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://stein.efishery.com/\"")
        }
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
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        javacOptions {
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(com.aej.buildsrc.Android.coreKtx)
    implementation(com.aej.buildsrc.Android.appCompat)
    implementation(com.aej.buildsrc.Android.material)
    implementation(com.aej.buildsrc.Android.constraintLayout)

    implementation(com.aej.buildsrc.Android.TestLibs.junit)
    implementation(com.aej.buildsrc.Android.TestLibs.extJunit)
    implementation(com.aej.buildsrc.Android.TestLibs.espressoCore)

    testImplementation(com.aej.buildsrc.Android.TestLibs.coreTesting)
    testImplementation(com.aej.buildsrc.Android.TestLibs.mockitoCore)
    testImplementation(com.aej.buildsrc.Android.TestLibs.mockitoKotlin)
    androidTestImplementation(com.aej.buildsrc.Android.TestLibs.mockitoAndroid)
    testImplementation(com.aej.buildsrc.Android.TestLibs.mockitoInline)

    implementation(com.aej.buildsrc.Android.Jetpack.activityKtx)
    implementation(com.aej.buildsrc.Android.Jetpack.lifecycleExtensions)
    implementation(com.aej.buildsrc.Android.Jetpack.livedataKtx)
    implementation(com.aej.buildsrc.Android.Jetpack.viewModelKtx)

    implementation(com.aej.buildsrc.Android.Network.retrofit)
    implementation(com.aej.buildsrc.Android.Network.retrofitAdapter)
    implementation(com.aej.buildsrc.Android.Network.okhttp)
    implementation(com.aej.buildsrc.Android.Network.okhttpLoggingInterceptor)
    implementation(com.aej.buildsrc.Android.Network.gson)
    implementation(com.aej.buildsrc.Android.Network.converterGson)

    implementation(com.aej.buildsrc.Android.ReactiveX.rxJava)
    implementation(com.aej.buildsrc.Android.ReactiveX.rxAndroid)
    implementation(com.aej.buildsrc.Android.ReactiveX.rxBinding)

    implementation(com.aej.buildsrc.Android.DI.hiltAndroid)
    kapt(com.aej.buildsrc.Android.DI.hiltCompiler)

    implementation(com.aej.buildsrc.Android.fastAdapter)
    implementation(com.aej.buildsrc.Android.fastAdapterBinding)
    implementation(com.aej.buildsrc.Android.fastAdapterUtils)
}
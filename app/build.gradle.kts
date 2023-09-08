@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.org.jetbrains.kotlin.serialization)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.org.jetbrains.koltinx.kover)
    alias(libs.plugins.kotlinx.parcelize)
}

android {
    namespace = "com.dev.rawgapps"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.dev.rawgapps"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.timber)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

    implementation(libs.room.ktx)
    kapt(libs.room.compiler)


    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.serialization.json)

    implementation(libs.coil)

    implementation(libs.navigation.compose)
    implementation(libs.navigation.hilt)

    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)

    testImplementation(libs.junit)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}

koverReport {
    filters {
        excludes {
            packages(
                "dagger.hilt.internal.aggregatedroot.codegen.*",
                "hilt_aggregated_deps.*",
                "com.dev.rawgapps.*.di.*",
                "com.dev.rawgapps.*.Hilt_*",
                "com.dev.rawgapps.*.*_Factory*",
                "com.dev.rawgapps.*.*_HiltModules*",
                "com.dev.rawgapps.*.*Module_*",
                "com.dev.rawgapps.*.*MembersInjector*",
                "com.dev.rawgapps.*.*_Impl*",
                "com.dev.rawgapps.ComposableSingletons*",
                "com.dev.rawgapps.BuildConfig*",
                "com.dev.rawgapps.*.Fake*",
                "com.dev.rawgapps.app.ComposableSingletons*"
            )

        }

    }
    defaults{
        // configure XML report
        xml{
            //  generate an XML report when running the `check` task
            onCheck = false
            // XML report file
            setReportFile(layout.buildDirectory.file("my-project-report/result.xml"))
        }

        html{
            // custom header in HTML reports, project path by default
            title = "Rawg Apps report title"

            //  generate a HTML report when running the `check` task
            onCheck = false

            // directory for HTML report
            setReportDir(layout.buildDirectory.dir("my-project-report/html-result"))
        }
    }
}

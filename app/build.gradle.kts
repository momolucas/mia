import io.gitlab.arturbosch.detekt.Detekt
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "lucas.momo.mia"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "lucas.momo.mia"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0-alpha"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
    }
    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
        }
        getByName("test") {
            java.srcDirs("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDirs("src/androidTest/kotlin")
        }
    }
    lint {
        abortOnError = true
        checkReleaseBuilds = true
        warningsAsErrors = true
        lintConfig = rootProject.file("config/lint.xml")
        sarifReport = true
        htmlReport = true
        xmlReport = false
        textReport = false
    }
}

ktlint {
    android.set(true)
    verbose.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    reporters {
        reporter(ReporterType.SARIF)
        reporter(ReporterType.HTML)
    }
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(rootProject.files("config/detekt.yml"))
    buildUponDefaultConfig = true
    allRules = false
    autoCorrect = true
}

tasks.withType<Detekt>().configureEach {
    reports {
        sarif.required.set(true)
        html.required.set(true)
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
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(project(":stocks"))
    implementation(project(":designsystem"))
}

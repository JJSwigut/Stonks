@Suppress("DSL_SCOPE_VIOLATION") // https://github.com/gradle/gradle/issues/22797 issue is fixed but can't update AGP yet.
plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.android)
    id("stonks.android.application")
    id("stonks.android.application.compose")
    id("stonks.quality")
}

android {
    namespace = "com.jjswigut.stonks"
}

dependencies {
    implementation(libs.bundles.koinDI)
    implementation(libs.bundles.ktorNetworking)
    implementation(libs.bundles.testRule)

    testImplementation(libs.bundles.test)
}

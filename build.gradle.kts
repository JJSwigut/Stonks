plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.detekt)
}

detekt {
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    reports {
        html.enabled = true
        xml.enabled = false
        txt.enabled = false
    }
}

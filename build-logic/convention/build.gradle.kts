plugins {
    `kotlin-dsl`
}

group = "com.jjswigut.stonks"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "stonks.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "stonks.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("spotless") {
            id = "stonks.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}

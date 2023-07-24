package com.jjswigut.stonks

import com.android.build.api.artifact.SingleArtifact.BUNDLE
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.io.File
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.utils.IMPLEMENTATION

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libraries.findVersion("composeCompilerVersion").get().toString()
        }

        dependencies {
            add(ConfigConstants.IMPLEMENTATION, libraries.findBundle("composeUI").get())
        }
    }
}

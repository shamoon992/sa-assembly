package com.saappcrafters.android

import com.android.build.api.dsl.ApplicationExtension
import com.saappcrafters.utils.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * A Gradle plugin that configures an Android application project to use Jetpack Compose.
 *
 * This plugin applies the following plugins:
 * * `com.android.application`
 * * `org.jetbrains.kotlin.plugin.compose`
 *
 * It also configures the `android` extension to enable Compose-specific features.
 *
 */
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }

        }
    }
}
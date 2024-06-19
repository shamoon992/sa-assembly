package com.saappcrafters.android

import com.android.build.gradle.LibraryExtension
import com.saappcrafters.utils.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * A Gradle plugin that applies the Android Library and Kotlin Compose plugins,
 * and configures the Android Library for Compose.
 *
 */
class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}
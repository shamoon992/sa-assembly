package com.saappcrafters.android

import com.saappcrafters.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


/**
 * A Gradle plugin that applies the Android Navigation Component conventions to a project.
 *
 * This plugin applies the `androidx.navigation.safeargs.kotlin` plugin and adds the necessary
 * dependencies for using the Android Navigation Component.
 *
 */
class AndroidNavigationComponentConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("androidx.navigation.safeargs.kotlin")

            dependencies {
                add("implementation", libs.findLibrary("navigation.fragment.ktx").get())
                add("implementation", libs.findLibrary("navigation.ui.ktx").get())
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
            }
        }
    }
}
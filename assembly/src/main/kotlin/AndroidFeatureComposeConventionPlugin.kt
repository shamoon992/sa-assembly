import com.android.build.gradle.LibraryExtension
import com.saappcrafters.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


/**
 * A Gradle plugin that applies the Android Feature Compose convention to a project.
 *
 * This plugin applies the following plugins:
 *
 * - `saappcrafters.android.library`
 * - `saappcrafters.android.library.compose`
 * - `saappcrafters.android.hilt`
 *
 * It also configures the Android Library extension with the following settings:
 *
 * - `testInstrumentationRunner`: `androidx.test.runner.AndroidJUnitRunner`
 *
 * And adds the following dependencies:
 *
 * - `implementation`: `libs.findLibrary("androidx.hilt.navigation.compose").get()`
 * - `implementation`: `libs.findLibrary("androidx.lifecycle.runtime.compose").get()`
 * - `implementation`: `libs.findLibrary("androidx.lifecycle.viewmodel.compose").get()`
 * - `implementation`: `libs.findLibrary("kotlinx.coroutines.android").get()`
 * - `testImplementation`: `libs.findLibrary("junit").get()`
 */
class AndroidFeatureComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("saappcrafters.android.library")
                apply("saappcrafters.android.library.compose")
                apply("saappcrafters.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())

                add("testImplementation", libs.findLibrary("junit").get())
            }
        }
    }
}
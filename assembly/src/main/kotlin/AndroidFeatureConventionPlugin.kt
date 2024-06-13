import com.android.build.gradle.LibraryExtension
import com.saappcrafters.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin


/**
 * A Gradle plugin that applies common Android feature conventions to a project.
 *
 * This plugin applies the following plugins:
 * - `saappcrafters.android.library`
 * - `saappcrafters.android.hilt`
 * - `saappcrafters.android.navigation`
 *
 * It also configures the following:
 * - Sets the `testInstrumentationRunner` to `androidx.test.runner.AndroidJUnitRunner`.
 * - Enables view binding.
 * - Adds various AndroidX and other common dependencies.
 *
 */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("saappcrafters.android.library")
                apply("saappcrafters.android.hilt")
                apply("saappcrafters.android.navigation")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                buildFeatures {
                    viewBinding = true
                }
            }

            dependencies {

                add("implementation", libs.findLibrary("androidx.appcompat").get())
                add("implementation", libs.findLibrary("androidx.corektx").get())
                add("implementation", libs.findLibrary("androidx.constraintlayout").get())
                add("implementation", libs.findLibrary("androidx.material").get())
                add("implementation", libs.findLibrary("androidx.fragment").get())
                add("implementation", libs.findLibrary("androidx.activity").get())
                add("implementation", libs.findLibrary("androidx.cardview").get())
                add("implementation", libs.findLibrary("androidx.recyclerview").get())

                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.savedstate").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.commonjava8").get())
                add("implementation", libs.findLibrary("dimension.sdp").get())
                add("implementation", libs.findLibrary("dimension.ssp").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
                add("implementation", libs.findLibrary("gson").get())

                add("testImplementation", kotlin("test"))
            }
        }
    }
}

import com.android.build.api.dsl.ApplicationExtension
import com.saappcrafters.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


/**
 * A Gradle plugin that configures an Android application project with the following features:
 * - Applies the `com.android.application` and `org.jetbrains.kotlin.android` plugins.
 * - Configures the Kotlin Android plugin.
 * - Sets the target SDK version to 34.
 *
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
            }
        }
    }

}
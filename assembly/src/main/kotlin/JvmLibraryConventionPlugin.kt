import com.saappcrafters.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * A Gradle plugin that applies the JVM library convention to a project.
 *
 * This plugin applies the `org.jetbrains.kotlin.jvm` plugin and configures the Kotlin JVM
 * compilation for the project.
 *
 */
class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}

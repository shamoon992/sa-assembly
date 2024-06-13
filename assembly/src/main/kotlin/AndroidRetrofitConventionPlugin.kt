import com.saappcrafters.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


/**
 * A Gradle plugin that applies the Android Retrofit convention.

 */
class AndroidRetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", libs.findLibrary("retrofit2.core").get())
                add("implementation", libs.findLibrary("retrofit2.moshiconverter").get())
                add("implementation", libs.findLibrary("okhttp3.core").get())
                add("implementation", libs.findLibrary("okhttp3.urlconnection").get())
            }
        }
    }
}
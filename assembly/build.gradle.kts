@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    alias(libs.plugins.gradle.plugin.publish)
}

group = "com.saappcrafters.android.build"
version = "1.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    jvmToolchain(17)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    implementation(libs.jgit)

    testImplementation(gradleTestKit())
    testImplementation(kotlin("test"))
    testImplementation(libs.bundles.junit.jupiter)

    testRuntimeOnly(libs.junit.jupiter.runtime)
}

gradlePlugin {
    website = "https://github.com/shamoon992/sa-assembly"
    vcsUrl = "https://github.com/shamoon992/sa-assembly"
    plugins {
        register("androidApplication") {
            id = "saappcrafters.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "saappcrafters.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "saappcrafters.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeatureCompose") {
            id = "saappcrafters.android.feature.compose"
            implementationClass = "AndroidFeatureComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "saappcrafters.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidRoom") {
            id = "saappcrafters.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidHilt") {
            id = "saappcrafters.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidRetrofit") {
            id = "saappcrafters.android.retrofit"
            implementationClass = "AndroidRetrofitConventionPlugin"
        }

        register("jvmLibrary") {
            id = "saappcrafters.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication> ("assembly") {
                groupId = "com.saappcrafters"
                artifactId = "assembly"
                version = project.version.toString()

                // Add the generated JAR file as an artifact
                from(components["java"])
            }
        }
        repositories {
            maven {
                name = "GithubPackages"
                url =  uri("https://maven.pkg.github.com/shamoon992/sa-assembly")
                credentials {
                    // Use GITHUB_ACTOR for username
                    username =  System.getenv("GITHUB_ACTOR")
                    // Use a personal access token for password
                    password =  System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}

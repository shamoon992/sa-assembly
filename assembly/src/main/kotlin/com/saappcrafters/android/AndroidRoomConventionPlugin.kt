package com.saappcrafters.android

import com.google.devtools.ksp.gradle.KspExtension
import com.saappcrafters.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.process.CommandLineArgumentProvider
import java.io.File


/**
 * A KSP plugin that configures Room database auto-migrations for Android projects.
 *
 * ## Usage
 *
 * To use this plugin, add the following to your project's `build.gradle.kts` file:
 *
 * ```kotlin
 * plugins {
 *     id("com.google.devtools.ksp") version "1.6.10-1.0.2"
 *     id("org.jetbrains.kotlin.kapt") version "1.6.10"
 *     id("com.squareup.sqldelight") version "1.5.3"
 *     id("com.android.library") version "7.1.1" apply false
 *     id("com.android.application") version "7.1.1" apply false
 *     id("com.google.devtools.ksp.gradle.plugin") version "1.7.10-1.0.6"
 * }
 *
 * dependencies {
 *     add("ksp", libs.findLibrary("room.compiler").get())
 * }
 * ```
 *
 * ## Explanation
 *
 * - The plugin applies the `com.google.devtools.ksp` plugin.
 * - It configures the `KspExtension` to use the `RoomSchemaArgProvider` for auto-migrations.
 * - It adds the necessary Room dependencies to the project.
 *
 * ## Auto-migrations
 *
 * Room auto-migrations allow you to update the schema of your database without losing data.
 * To use auto-migrations, you need to export the schemas for each version of your database to the `schemas` directory.
 * See the [Room documentation](https://developer.android.com/reference/kotlin/androidx/room/AutoMigration) for more information.
 *
 * ## Known Issues
 *
 * - There is a known issue with KSP and Room auto-migrations.
 * See [this issue](https://issuetracker.google.com/issues/132245929) for more information.
 *
 */
class AndroidRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<KspExtension> {
                // The schemas directory contains a schema file for each version of the Room database.
                // This is required to enable Room auto migrations.
                // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
                // arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
            }

            dependencies {
                add("implementation", libs.findLibrary("room.runtime").get())
                add("implementation", libs.findLibrary("room.ktx").get())
                add("ksp", libs.findLibrary("room.compiler").get())
            }
        }
    }

    /**
     * https://issuetracker.google.com/issues/132245929
     * [Export schemas](https://developer.android.com/training/data-storage/room/migrating-db-versions#export-schemas)
     */
    class RoomSchemaArgProvider(
        @get:InputDirectory
        @get:PathSensitive(PathSensitivity.RELATIVE)
        val schemaDir: File,
    ) : CommandLineArgumentProvider {
        override fun asArguments() = listOf("room.schemaLocation=${schemaDir.path}")
    }
}
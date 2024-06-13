package com.saappcrafters.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

// Extension property to access the "libs" VersionCatalog for a given Project.
val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.saplugins
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("plugins")
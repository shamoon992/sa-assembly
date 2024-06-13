package com.saappcrafters.utils

import org.gradle.api.JavaVersion
import org.gradle.jvm.toolchain.JavaLanguageVersion

const val ANDROID_TARGET_SDK = 35

const val ANDROID_MIN_SDK = 33

const val ANDROID_COMPILE_SDK = 35

internal val JAVA_TOOLCHAIN_VERSION = JavaLanguageVersion.of(17)
internal val JAVA_LANGUAGE_VERSION = JavaVersion.VERSION_17
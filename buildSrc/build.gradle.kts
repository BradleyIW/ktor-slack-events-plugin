private object Dependencies {
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20"
    const val detektGradlePlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.9.1"
}

plugins {
    java
    `maven-publish`
    `kotlin-dsl`
    kotlin("jvm") version "1.7.20"
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(Dependencies.kotlinGradlePlugin)
    implementation(Dependencies.detektGradlePlugin)
}

package scripts.application

import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.java

plugins {
    java apply false
}

java {
    withJavadocJar()
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

package scripts.application

import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("jvm") apply false
}

rootProject.configure<SourceSetContainer> {
    all {
        map {
            it.kotlin.srcDir("src/${it.name}/kotlin")
            it.resources.srcDir("src/${it.name}/resources")
        }
    }
}
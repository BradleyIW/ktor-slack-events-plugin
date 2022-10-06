import org.gradle.internal.Cast.uncheckedCast

plugins {
    id(ScriptPlugins.applicationConfig)
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "io.github.bradleyiw"
version = "0.1.0-SNAPSHOT"

val getSigningPropertiesValue = uncheckedCast<(String) -> String?>(extra["getSigningPropertiesValue"])

repositories {
    mavenCentral()
    mavenLocal()
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(getSigningPropertiesValue?.invoke("ossrhUsername"))
            password.set(getSigningPropertiesValue?.invoke("ossrhPassword"))
        }
    }
}

dependencies {
    implementation(Libraries.slackBoltSDK)
    testImplementation(platform(TestLibraries.junitBom))
    testImplementation(TestLibraries.junitPlatformRunner)
    testImplementation(TestLibraries.jupiterMockito)
    testImplementation(TestLibraries.jupiterApi)
}

tasks.test {
    useJUnitPlatform()
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
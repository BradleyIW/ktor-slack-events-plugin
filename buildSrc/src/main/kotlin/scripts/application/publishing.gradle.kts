package scripts.application

plugins {
    `maven-publish` apply false
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "ktor-slack-events-plugin"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("Ktor Slack Events Plugins")
                description.set("A convenient Ktor plugin to help organise event callbacks for the Java Slack SDK.")
                packaging = "jar"

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set("BradleyIW")
                        name.set("Bradley Wilson")
                        email.set("biw1805@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/BradleyIW/ktor-slack-events-plugin.git")
                    developerConnection.set("scm:git:ssh://github.com/BradleyIW/ktor-slack-events-plugin.git")
                }
            }
        }
    }
}
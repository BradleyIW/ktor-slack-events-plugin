package scripts.application

import org.gradle.internal.Cast.uncheckedCast

plugins {
    `maven-publish` apply false
    signing apply false
    id("scripts.config.properties")
}

val getSigningPropertiesValue = uncheckedCast<(String) -> String?>(extra["getSigningPropertiesValue"])

signing {
    val signingKeyId = getSigningPropertiesValue?.invoke("signing.keyId") ?: System.getenv("GPG_KEY_ID")
    val signingKey = getSigningPropertiesValue?.invoke("signing.key") ?: System.getenv("GPG_KEY")
    val signingPassword = getSigningPropertiesValue?.invoke("signing.password") ?: System.getenv("GPG_KEY_PASSWORD")
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications["mavenJava"])
}

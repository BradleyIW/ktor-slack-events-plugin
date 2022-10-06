package scripts.config

import java.io.FileInputStream
import java.util.*

val signingProperties = Properties()

if (signingPropertiesExists()) {
    val signingPropertiesFile = rootProject.file("signing.properties")
    signingProperties.load(FileInputStream(signingPropertiesFile))
}

extra["getSigningPropertiesValue"] = { key: String ->
    if (signingPropertiesExists()) {
        (signingProperties[key] as String).trim()
    } else " "
}

fun signingPropertiesExists() =
    rootProject.file("signing.properties").exists()
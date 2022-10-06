object Versions {
    const val kotlin = "1.6.10"
    const val slackSdkVersion = "1.25.1"
    const val detekt = "1.20.0"
    const val mockitoVersion = "4.0.0"
    const val junitPlatform = "1.9.0"
    const val jupiterVersion = "5.9.0"
}

object ScriptPlugins {
    const val applicationConfig = "scripts.application.config"
}

object Libraries {
    const val slackBoltSDK = "com.slack.api:bolt-ktor:${Versions.slackSdkVersion}"
}

object TestLibraries {
    const val junitBom = "org.junit:junit-bom:${Versions.jupiterVersion}"
    const val jupiterApi = "org.junit.jupiter:junit-jupiter:${Versions.jupiterVersion}"
    const val junitPlatformRunner = "org.junit.platform:junit-platform-runner:${Versions.junitPlatform}"
    const val jupiterMockito = "org.mockito:mockito-junit-jupiter:${Versions.mockitoVersion}"
}
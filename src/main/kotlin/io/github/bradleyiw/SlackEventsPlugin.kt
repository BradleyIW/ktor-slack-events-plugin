package io.github.bradleyiw

import com.slack.api.bolt.App
import io.ktor.server.application.*
import io.ktor.server.application.hooks.*

class PluginConfiguration {

    lateinit var app: App
    private var slackActions: List<SlackEvent> = emptyList()

    var actions: List<SlackEvent> = emptyList()

    fun containers(vararg containers: SlackEventsContainer) {
        slackActions = containers
            .map {
                if (it.isActive) {
                    it.actions
                } else {
                    emptyList()
                }
            }.flatten()
    }
}

val SlackEventsPlugin = createApplicationPlugin(
    name = "SlackActionPlugin",
    createConfiguration = ::PluginConfiguration
) {
    on(MonitoringEvent(ApplicationStarted)) { application ->
        pluginConfig.actions.forEach {
            it.execute(pluginConfig.app)
        }
        application.log.info("Slack actions registered")
    }
    on(MonitoringEvent(ApplicationStopped)) { application ->
        pluginConfig.actions = emptyList()

        // Release resources and unsubscribe from events
        application.environment.monitor.unsubscribe(ApplicationStarted) {}
        application.environment.monitor.unsubscribe(ApplicationStopped) {}
    }
}
package io.github.bradleyiw.commands

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.SlashCommandContext
import com.slack.api.bolt.request.builtin.SlashCommandRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class SlashCommandSlackEvent(
    private val command: String,
) : SlackEventRunnable<SlashCommandRequest, SlashCommandContext>(), SlackEvent {

    override fun execute(app: App) {
        app.command(command) { req, context ->
            run(req, context)
        }
    }
}
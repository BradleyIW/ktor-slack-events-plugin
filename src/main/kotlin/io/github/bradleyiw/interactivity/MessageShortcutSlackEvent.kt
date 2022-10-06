package io.github.bradleyiw.interactivity

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.MessageShortcutContext
import com.slack.api.bolt.request.builtin.MessageShortcutRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class MessageShortcutSlackEvent(
    private val callbackId: String,
) : SlackEventRunnable<MessageShortcutRequest, MessageShortcutContext>(),
    SlackEvent {

    override fun execute(app: App) {
        app.messageShortcut(callbackId) { req, context ->
            run(req, context)
        }
    }
}
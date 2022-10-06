package io.github.bradleyiw.interactivity

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.GlobalShortcutContext
import com.slack.api.bolt.request.builtin.GlobalShortcutRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class GlobalShortcutSlackEvent(
    private val callbackId: String,
) : SlackEventRunnable<GlobalShortcutRequest, GlobalShortcutContext>(),
    SlackEvent {

    override fun execute(app: App) {
        app.globalShortcut(callbackId) { req, context ->
            run(req, context)
        }
    }
}
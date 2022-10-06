package io.github.bradleyiw.blockkit

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.ActionContext
import com.slack.api.bolt.request.builtin.BlockActionRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class BlockActionSlackEvent(
    private val actionId: String,
) : SlackEventRunnable<BlockActionRequest, ActionContext>(), SlackEvent {

    override fun execute(app: App) {
        app.blockAction(actionId) { req, context ->
            run(req, context)
        }
    }
}
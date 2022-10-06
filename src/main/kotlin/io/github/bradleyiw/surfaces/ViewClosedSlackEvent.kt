package io.github.bradleyiw.surfaces

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.DefaultContext
import com.slack.api.bolt.request.builtin.ViewClosedRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class ViewClosedSlackEvent(
    private val actionId: String,
) : SlackEventRunnable<ViewClosedRequest, DefaultContext>(), SlackEvent {

    override fun execute(app: App) {
        app.viewClosed(actionId) { req, context ->
            run(req, context)
        }
    }
}

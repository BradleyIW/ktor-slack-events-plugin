package io.github.bradleyiw.surfaces

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.ViewSubmissionContext
import com.slack.api.bolt.request.builtin.ViewSubmissionRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class ViewSubmissionSlackEvent(
    private val actionId: String,
) : SlackEventRunnable<ViewSubmissionRequest, ViewSubmissionContext>(),
    SlackEvent {

    override fun execute(app: App) {
        app.viewSubmission(actionId) { req, context ->
            run(req, context)
        }
    }
}

package io.github.bradleyiw.messaging

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.AttachmentActionContext
import com.slack.api.bolt.request.builtin.AttachmentActionRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class AttachmentActionSlackEvent(
    private val callbackId: String,
) : SlackEventRunnable<AttachmentActionRequest, AttachmentActionContext>(),
    SlackEvent {

    override fun execute(app: App) {
        app.attachmentAction(callbackId) { req, context ->
            run(req, context)
        }
    }
}
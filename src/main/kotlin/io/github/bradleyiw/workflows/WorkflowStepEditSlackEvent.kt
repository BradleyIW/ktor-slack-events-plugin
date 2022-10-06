package io.github.bradleyiw.workflows

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.WorkflowStepEditContext
import com.slack.api.bolt.request.builtin.WorkflowStepEditRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class WorkflowStepEditSlackEvent(
    private val callbackId: String,
) : SlackEventRunnable<WorkflowStepEditRequest, WorkflowStepEditContext>(),
    SlackEvent {

    override fun execute(app: App) {
        app.workflowStepEdit(callbackId) { req, context ->
            run(req, context)
        }
    }
}
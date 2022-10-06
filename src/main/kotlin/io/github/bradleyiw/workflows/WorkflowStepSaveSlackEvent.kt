package io.github.bradleyiw.workflows

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.WorkflowStepSaveContext
import com.slack.api.bolt.request.builtin.WorkflowStepSaveRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class WorkflowStepSaveSlackEvent(
    private val callbackId: String,
) : SlackEventRunnable<WorkflowStepSaveRequest, WorkflowStepSaveContext>(), SlackEvent {

    override fun execute(app: App) {
        app.workflowStepSave(callbackId) { req, context ->
            run(req, context)
        }
    }
}
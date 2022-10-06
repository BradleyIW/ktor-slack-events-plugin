package io.github.bradleyiw.workflows

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.WorkflowStepExecuteContext
import com.slack.api.bolt.request.builtin.WorkflowStepExecuteRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class WorkflowStepExecuteSlackEvent(
    private val callbackId: String,
) : SlackEventRunnable<WorkflowStepExecuteRequest, WorkflowStepExecuteContext>(),
    SlackEvent {

    override fun execute(app: App) {
        app.workflowStepExecute(callbackId) { req, context ->
            run(req, context)
        }
    }
}
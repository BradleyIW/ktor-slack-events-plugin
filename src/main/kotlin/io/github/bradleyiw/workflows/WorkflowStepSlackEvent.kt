package io.github.bradleyiw.workflows

import com.slack.api.bolt.App
import com.slack.api.bolt.middleware.builtin.WorkflowStep
import io.github.bradleyiw.SlackEvent

abstract class WorkflowStepSlackEvent : SlackEvent {

    abstract fun step(): WorkflowStep

    override fun execute(app: App) {
        app.step(step())
    }
}
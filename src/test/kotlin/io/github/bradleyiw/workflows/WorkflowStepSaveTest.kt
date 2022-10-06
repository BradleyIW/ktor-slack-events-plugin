package io.github.bradleyiw.workflows

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.WorkflowStepSaveContext
import com.slack.api.bolt.request.builtin.WorkflowStepSaveRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito

internal class WorkflowStepSaveTest : UnitTest() {

    private class TestWorkflowStepSave : WorkflowStepSaveSlackEvent(TEST_WORKFLOW_STEP_SAVE_CALLBACK_ID) {
        override fun run(req: WorkflowStepSaveRequest, ctx: WorkflowStepSaveContext): Response? =
            ctx.ack()
    }

    private val action: WorkflowStepSaveSlackEvent = TestWorkflowStepSave()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert workflowStepSave is called`() {
        action.execute(app)
        Mockito.verify(app).workflowStepSave(eq(TEST_WORKFLOW_STEP_SAVE_CALLBACK_ID), any())
    }

    companion object {
        private const val TEST_WORKFLOW_STEP_SAVE_CALLBACK_ID = "test-workflow-step-save-callback-id"
    }
}
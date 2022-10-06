package io.github.bradleyiw.workflows

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.WorkflowStepEditContext
import com.slack.api.bolt.request.builtin.WorkflowStepEditRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class WorkflowStepEditTest : UnitTest() {

    private class TestWorkflowStepEdit : WorkflowStepEditSlackEvent(TEST_WORKFLOW_STEP_EDIT_CALLBACK_ID) {
        override fun run(req: WorkflowStepEditRequest, ctx: WorkflowStepEditContext): Response? =
            ctx.ack()
    }

    private val action: WorkflowStepEditSlackEvent = TestWorkflowStepEdit()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert workflowStepEdit is called`() {
        action.execute(app)
        verify(app).workflowStepEdit(eq(TEST_WORKFLOW_STEP_EDIT_CALLBACK_ID), any())
    }

    companion object {
        private const val TEST_WORKFLOW_STEP_EDIT_CALLBACK_ID = "test-workflow-step-edit-callback-id"
    }
}

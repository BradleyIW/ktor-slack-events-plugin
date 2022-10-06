package io.github.bradleyiw.workflows

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.WorkflowStepExecuteContext
import com.slack.api.bolt.request.builtin.WorkflowStepExecuteRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class WorkflowStepExecuteTest : UnitTest() {

    private class TestWorkflowStepExecute : WorkflowStepExecuteSlackEvent(TEST_WORKFLOW_STEP_EXECUTE_CALLBACK_ID) {
        override fun run(req: WorkflowStepExecuteRequest, ctx: WorkflowStepExecuteContext): Response? =
            ctx.ack()
    }

    private val action: WorkflowStepExecuteSlackEvent = TestWorkflowStepExecute()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert workflowStepExecute is called`() {
        action.execute(app)
        verify(app).workflowStepExecute(eq(TEST_WORKFLOW_STEP_EXECUTE_CALLBACK_ID), any())
    }

    companion object {
        private const val TEST_WORKFLOW_STEP_EXECUTE_CALLBACK_ID = "test-workflow-step-execute-callback-id"
    }
}

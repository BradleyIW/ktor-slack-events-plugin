package io.github.bradleyiw.workflows

import com.slack.api.bolt.App
import com.slack.api.bolt.middleware.builtin.WorkflowStep
import io.github.bradleyiw.framework.capture
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class WorkflowStepTest : UnitTest() {

    @Mock
    private lateinit var app: App

    @Captor
    private lateinit var stepCaptor: ArgumentCaptor<WorkflowStep>

    private class TestWorkflowStep : WorkflowStepSlackEvent() {

        override fun step(): WorkflowStep =
            WorkflowStep
                .builder()
                .callbackId(TEST_WORKFLOW_STEP_CALLBACK_ID)
                .build()
    }

    private val action: WorkflowStepSlackEvent = TestWorkflowStep()

    @Test
    fun `Given execute, then assert step is called`() {
        action.execute(app)

        verify(app).step(capture(stepCaptor))

        assertTrue(stepCaptor.value.callbackId == TEST_WORKFLOW_STEP_CALLBACK_ID)
    }

    companion object {
        private const val TEST_WORKFLOW_STEP_CALLBACK_ID = "test-workflow-step-callback-id"
    }
}
package io.github.bradleyiw.surfaces

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.ViewSubmissionContext
import com.slack.api.bolt.request.builtin.ViewSubmissionRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class ViewSubmissionTest : UnitTest() {

    private class TestViewSubmission : ViewSubmissionSlackEvent(TEST_VIEW_SUBMISSION_ACTION_ID) {
        override fun run(req: ViewSubmissionRequest, ctx: ViewSubmissionContext): Response? =
            ctx.ack()
    }

    private val action: ViewSubmissionSlackEvent = TestViewSubmission()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert viewSubmission is called`() {
        action.execute(app)
        verify(app).viewSubmission(eq(TEST_VIEW_SUBMISSION_ACTION_ID), any())
    }

    companion object {
        private const val TEST_VIEW_SUBMISSION_ACTION_ID = "test-view-submission-action-id"
    }
}
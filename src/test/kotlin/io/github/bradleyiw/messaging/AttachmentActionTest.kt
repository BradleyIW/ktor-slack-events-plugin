package io.github.bradleyiw.messaging

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.AttachmentActionContext
import com.slack.api.bolt.request.builtin.AttachmentActionRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class AttachmentActionTest : UnitTest() {

    private class TestAttachmentAction : AttachmentActionSlackEvent(TEST_ATTACHMENT_ACTION_CALLBACK_ID) {
        override fun run(req: AttachmentActionRequest, ctx: AttachmentActionContext): Response? =
            ctx.ack()
    }

    private val action: AttachmentActionSlackEvent = TestAttachmentAction()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert attachmentAction is called`() {
        action.execute(app)
        verify(app).attachmentAction(eq(TEST_ATTACHMENT_ACTION_CALLBACK_ID), any())
    }

    companion object {
        private const val TEST_ATTACHMENT_ACTION_CALLBACK_ID = "test-attachment-action-callback-id"
    }
}
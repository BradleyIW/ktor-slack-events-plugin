package io.github.bradleyiw.interactivity

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.MessageShortcutContext
import com.slack.api.bolt.request.builtin.MessageShortcutRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class MessageShortcutTest : UnitTest() {

    private class TestMessageShortcut : MessageShortcutSlackEvent(TEST_MESSAGE_SHORTCUT_CALLBACK_ID) {
        override fun run(req: MessageShortcutRequest, ctx: MessageShortcutContext): Response? =
            ctx.ack()
    }

    private val action: MessageShortcutSlackEvent = TestMessageShortcut()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert messageShortcut is called`() {
        action.execute(app)
        verify(app).messageShortcut(eq(TEST_MESSAGE_SHORTCUT_CALLBACK_ID), any())
    }

    companion object {
        private const val TEST_MESSAGE_SHORTCUT_CALLBACK_ID = "test-message-shortcut-callback-id"
    }
}
package io.github.bradleyiw.interactivity

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.GlobalShortcutContext
import com.slack.api.bolt.request.builtin.GlobalShortcutRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class GlobalShortcutTest : UnitTest() {

    private class TestGlobalShortcut : GlobalShortcutSlackEvent(TEST_GLOBAL_SHORTCUT_CALLBACK_ID) {
        override fun run(req: GlobalShortcutRequest, ctx: GlobalShortcutContext): Response? =
            ctx.ack()
    }

    private val action: GlobalShortcutSlackEvent = TestGlobalShortcut()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert globalShortcut is called`() {
        action.execute(app)
        verify(app).globalShortcut(eq(TEST_GLOBAL_SHORTCUT_CALLBACK_ID), any())
    }

    companion object {
        private const val TEST_GLOBAL_SHORTCUT_CALLBACK_ID = "test-global-shortcut-callback-id"
    }
}
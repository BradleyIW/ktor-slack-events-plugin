package io.github.bradleyiw.commands

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.SlashCommandContext
import com.slack.api.bolt.request.builtin.SlashCommandRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class SlashCommandTest : UnitTest() {

    private class TestSlashCommand : SlashCommandSlackEvent(TEST_SLASH_COMMAND) {
        override fun run(req: SlashCommandRequest, ctx: SlashCommandContext): Response? =
            ctx.ack()
    }

    private val action: SlashCommandSlackEvent = TestSlashCommand()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert command is called`() {
        action.execute(app)
        verify(app).command(eq(TEST_SLASH_COMMAND), any())
    }

    companion object {
        private const val TEST_SLASH_COMMAND = "/test"
    }
}
package io.github.bradleyiw.blockkit

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.ActionContext
import com.slack.api.bolt.request.builtin.BlockActionRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class BlockActionTest : UnitTest() {

    private class TestBlockAction : BlockActionSlackEvent(TEST_BLOCK_ACTION_ACTION_ID) {
        override fun run(req: BlockActionRequest, ctx: ActionContext): Response? =
            ctx.ack()
    }

    private val action: BlockActionSlackEvent = TestBlockAction()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert blockAction is called`() {
        action.execute(app)
        verify(app).blockAction(eq(TEST_BLOCK_ACTION_ACTION_ID), any())
    }

    companion object {
        private const val TEST_BLOCK_ACTION_ACTION_ID = "test-block-action-id"
    }
}
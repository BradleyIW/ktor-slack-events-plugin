package io.github.bradleyiw.blockkit

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.BlockSuggestionContext
import com.slack.api.bolt.request.builtin.BlockSuggestionRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class BlockSuggestionTest : UnitTest() {

    private class TestBlockSuggestion : BlockSuggestionSlackEvent(TEST_BLOCK_SUGGESTION_ACTION_ID) {
        override fun run(req: BlockSuggestionRequest, ctx: BlockSuggestionContext): Response? =
            ctx.ack()
    }

    private val action: BlockSuggestionSlackEvent = TestBlockSuggestion()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert blockSuggestion is called`() {
        action.execute(app)
        verify(app).blockSuggestion(eq(TEST_BLOCK_SUGGESTION_ACTION_ID), any())
    }

    companion object {
        private const val TEST_BLOCK_SUGGESTION_ACTION_ID = "test-block-suggestion-action-id"
    }
}
package io.github.bradleyiw.blockkit

import com.slack.api.bolt.context.builtin.ActionContext
import com.slack.api.bolt.request.builtin.BlockActionRequest
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class SimpleBlockActionSlackEventTest : UnitTest() {

    private lateinit var simpleBlockActionSlackEvent: SimpleBlockActionSlackEvent

    @Mock
    private lateinit var context: ActionContext

    @Mock
    private lateinit var request: BlockActionRequest

    @BeforeEach
    fun setup() {
        simpleBlockActionSlackEvent = SimpleBlockActionSlackEvent(SIMPLE_BLOCK_ACTION_ACTION_ID)
    }

    @Test
    fun `given run is called, when actionId is provided, then acknowledge callback`() {
        simpleBlockActionSlackEvent.run(request, context)

        verify(context).ack()
    }

    companion object {
        private const val SIMPLE_BLOCK_ACTION_ACTION_ID = "simple-block-action-action-id"
    }
}
package io.github.bradleyiw.surfaces

import com.slack.api.bolt.context.builtin.DefaultContext
import com.slack.api.bolt.request.builtin.ViewClosedRequest
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class SimpleViewClosedSlackEventTest : UnitTest() {

    private lateinit var simpleViewClosedSlackEvent: SimpleViewClosedSlackEvent

    @Mock
    private lateinit var context: DefaultContext

    @Mock
    private lateinit var request: ViewClosedRequest

    @BeforeEach
    fun setup() {
        simpleViewClosedSlackEvent = SimpleViewClosedSlackEvent(SIMPLE_VIEW_CLOSED_CALLBACK_ID)
    }

    @Test
    fun `given run is called, when callback_id is provided, then acknowledge callback`() {
        simpleViewClosedSlackEvent.run(request, context)

        verify(context).ack()
    }

    companion object {
        private const val SIMPLE_VIEW_CLOSED_CALLBACK_ID = "simple-view-closed-callback-id"
    }
}
package io.github.bradleyiw.events

import com.slack.api.app_backend.events.payload.EventsApiPayload
import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.EventContext
import com.slack.api.bolt.response.Response
import com.slack.api.model.event.MessageEvent
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class EventTest : UnitTest() {

    private class TestEvent : EventSlackEvent<MessageEvent>(MessageEvent::class) {
        override fun run(req: EventsApiPayload<MessageEvent>, ctx: EventContext): Response? =
            ctx.ack()

    }

    private val action: EventSlackEvent<MessageEvent> = TestEvent()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert event is called`() {
        action.execute(app)
        verify(app).event(eq(MessageEvent::class.java), any())
    }
}
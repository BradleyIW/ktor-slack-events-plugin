package io.github.bradleyiw.events

import com.slack.api.app_backend.events.payload.EventsApiPayload
import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.EventContext
import com.slack.api.model.event.Event
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable
import kotlin.reflect.KClass

abstract class EventSlackEvent<E : Event>(
    private val eventClass: KClass<E>,
) : SlackEventRunnable<EventsApiPayload<E>, EventContext>(), SlackEvent {

    override fun execute(app: App) {
        app.event(eventClass.java) { req, context ->
            run(req, context)
        }
    }
}
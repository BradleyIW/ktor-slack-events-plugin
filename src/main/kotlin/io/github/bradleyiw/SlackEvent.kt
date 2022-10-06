package io.github.bradleyiw

import com.slack.api.bolt.App
import com.slack.api.bolt.response.Response

interface SlackEvent {
    fun execute(app: App)
}

abstract class SlackEventRunnable<Request, Context> {
    abstract fun run(req: Request, ctx: Context): Response?
}

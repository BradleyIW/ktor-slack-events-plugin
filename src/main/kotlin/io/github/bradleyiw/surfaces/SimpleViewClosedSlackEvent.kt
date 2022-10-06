package io.github.bradleyiw.surfaces

import com.slack.api.bolt.context.builtin.DefaultContext
import com.slack.api.bolt.request.builtin.ViewClosedRequest
import com.slack.api.bolt.response.Response

class SimpleViewClosedSlackEvent(actionId: String) : ViewClosedSlackEvent(actionId) {
    override fun run(req: ViewClosedRequest, ctx: DefaultContext): Response? = ctx.ack()
}
package io.github.bradleyiw.blockkit

import com.slack.api.bolt.context.builtin.ActionContext
import com.slack.api.bolt.request.builtin.BlockActionRequest
import com.slack.api.bolt.response.Response

class SimpleBlockActionSlackEvent(actionId: String) : BlockActionSlackEvent(actionId) {
    override fun run(req: BlockActionRequest, ctx: ActionContext): Response? = ctx.ack()
}
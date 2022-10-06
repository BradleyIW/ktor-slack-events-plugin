package io.github.bradleyiw.blockkit

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.BlockSuggestionContext
import com.slack.api.bolt.request.builtin.BlockSuggestionRequest
import io.github.bradleyiw.SlackEvent
import io.github.bradleyiw.SlackEventRunnable

abstract class BlockSuggestionSlackEvent(
    private val actionId: String,
) : SlackEventRunnable<BlockSuggestionRequest, BlockSuggestionContext>(),
    SlackEvent {

    override fun execute(app: App) {
        app.blockSuggestion(actionId) { req, context ->
            run(req, context)
        }
    }
}
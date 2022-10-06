package io.github.bradleyiw.surfaces

import com.slack.api.bolt.App
import com.slack.api.bolt.context.builtin.DefaultContext
import com.slack.api.bolt.request.builtin.ViewClosedRequest
import com.slack.api.bolt.response.Response
import io.github.bradleyiw.framework.any
import io.github.bradleyiw.framework.eq
import io.github.bradleyiw.framework.UnitTest
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class ViewClosedTest : UnitTest() {

    private class TestViewClosed : ViewClosedSlackEvent(TEST_VIEW_CLOSED_ACTION_ID) {
        override fun run(req: ViewClosedRequest, ctx: DefaultContext): Response? = ctx.ack()
    }

    private val action: ViewClosedSlackEvent = TestViewClosed()

    @Mock
    private lateinit var app: App

    @Test
    fun `Given execute, then assert viewClosed is called`() {
        action.execute(app)
        verify(app).viewClosed(eq(TEST_VIEW_CLOSED_ACTION_ID), any())
    }

    companion object {
        private const val TEST_VIEW_CLOSED_ACTION_ID = "test-view-closed-action-id"
    }
}
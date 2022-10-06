# KTOR Slack Events Plugin

A Ktor plugin that allows you to write cleaner code whilst building Slack bots with Ktor. 
A simple wrapper around the event driven design Slack API follows with some classes to help 
you focus on building and writing cleaner more testable code without having to inject `SlackApp` everywhere.

In order to use this plugin, you'll need to be familiar with the [Java Slack SDK](https://github.com/slackapi/java-slack-sdk) (including Bolt for Java) and it's functionality.

```
implementation("io.github.bradleyiw:ktor-slack-events-plugin:0.1.0-SNAPSHOT")
```

# Table of Contents
1. [Install Plugin](#install-plugin)
2. [Add Containers](#add-event-containers)
3. [Events](#events)
   1. [Default Events](#supported-events)
   2. [Simple Events](#simple-events)

## Install Plugin
Once you've added the library to your dependencies. You'll need to install the plugin into your Ktor application:

```
install(SlackEventsPlugin) {
    app = slackApp
    containers(
        //Go to next section
    )
}
```

## Add Event Containers
To allow for a more flexible design to suit your architecture needs, you can organise containers however you see fit. 

```
val messagesEvents: SlackEventsContainer = container {
    events(
        ClickSendMessageButtonEvent(), 
        RecieveMessageEvent()
    )
}
```

You can also deactivate containers if you need to switch off events: 

```
val messagesEvents: SlackEventsContainer = container {
    isActive(isStaging())
    events(
        ClickSendMessageButtonEvent(), 
        RecieveMessageEvent()
    )
}
```

Then you would simply add ``messagesEvents`` container to the containers function where you installed the plugin. 

## Events

The events from the above example could look something like:

```
class RecieveMessageEvent : EventSlackEvent<MessageEvent>(MessageEvent::class) {

    override fun run(req: EventsApiPayload<MessageEvent>, ctx: EventContext): Response? {
        // Action when recieved event
        return ctx.ack()
    }
}
```

```
class ClickSendMessageButtonEvent : BlockActionSlackEvent(MessageEventId.SEND_MESSAGE_ACTION_ID.id) {

    override fun run(req: BlockActionRequest, ctx: ActionContext): Response? {
        return ctx.say { message ->
            message
                .text("Hello World!")
                .token(ctx.botToken)
                .channel(ctx.requestUserId)
                .blocks(withBlocks {
                    section {
                        markdownText("*Hello World*")
                    }
                })
        }
    }

```

### Supported Events
This plugin support various Slack events:

 - `BlockActionSlackEvent` replaces `app.blockAction(...)` events. 
 - `BlockSuggestionSlackEvent` replaces `app.blockSuggestion(...)` events.
 - `SlashCommandActionEvent` replaces `app.command(...)` events.
 - `EventSlackEvent` replaces `app.event(...)` events.
 - `GlobalShortcutSlackEvent` replaces `app.globalShortcut(...)` events.
 - `MessageShortcutSlackEvent` replaces `app.messageShortcut(...)` events.
 - `AttachmentActionSlackEvent` replaces `app.attachmentAction(...)` events.
 - `ViewClosedSlackEvent` replaces `app.viewClosed(...)` events.
 - `ViewSubmissionSlackEvent` replaces `app.viewSubmission(...)` events.
 - `WorkflowStepEditSlackEvent` replaces `app.workflowStepEdit(...)` events.
 - `WorkflowStepExecuteSlackEvent` replaces `app.workflowStepExecute(...)` events.
 - `WorkflowStepSaveSlackEvent` replaces `app.workflowStepSave(...)` events.
 - `WorkflowStepSlackEvent` replaces `app.step(...)` events.


### Simple Events

For some scenarios where you may receive event data through a submission context or a context outside the component the user is interacting with. You can use predefined SlackEvents to return a simple `ctx.ack()` response.

- SimpleBlockActionSlackEvent:
   ```
     SimpleBlockActionSlackEvent(SimpleEventId.SIMPLE_BLOCK_ACTION_ACTION_ID.id)
   ```

- SimpleViewClosedSlackEvent:
   ```
     SimpleViewClosedSlackEvent(SimpleEventId.SIMPLE_VIEW_CLOSED_CALLBACK_ID.id)
   ```
  
Feel free to raise an issue if you want me to add more. 
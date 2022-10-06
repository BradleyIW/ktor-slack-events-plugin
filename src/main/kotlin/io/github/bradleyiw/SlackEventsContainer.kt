package io.github.bradleyiw

typealias ContainerDeclaration = SlackEventsContainer.() -> Unit

class SlackEventsContainer {
    var isActive = true;
    var actions: List<SlackEvent> = mutableListOf()

    fun events(vararg newEvents: SlackEvent): List<SlackEvent> =
        newEvents
            .toList()
            .also { actions = it }

    fun isActive(isActive: Boolean) {
        this.isActive = isActive
    }
}

fun container(containerDeclaration: ContainerDeclaration): SlackEventsContainer {
    val container = SlackEventsContainer()
    containerDeclaration(container)
    return container
}

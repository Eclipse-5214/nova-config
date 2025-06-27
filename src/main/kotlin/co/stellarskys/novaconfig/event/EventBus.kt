package co.stellarskys.novaconfig.event

object EventBus {
    private val events = mutableMapOf<String, Event<Any>>()

    fun <T : Any> createEvent(
        name: String,
        setup: (Event<T>.() -> Unit)? = null
    ): Event<T> {
        val event = Event<T>()
        events[name] = event as Event<Any>
        setup?.invoke(event)
        return event
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getEvent(name: String): Event<T>? {
        return events[name] as? Event<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> register(eventName: String, callback: (T) -> Unit) {
        val event = events[eventName] as? Event<T>
            ?: error("No event registered with name '$eventName'")
        event.register(callback)
    }
}
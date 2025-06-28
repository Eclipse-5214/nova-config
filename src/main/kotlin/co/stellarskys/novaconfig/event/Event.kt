package co.stellarskys.novaconfig.event

typealias Callback<T> = (T) -> Unit
typealias CustomCallback<T> = (Callback<T>) -> EventHandle

class Event<T : Any>(
    val name: String,
    vararg val args: Any?,
    val callback: Callback<T>,
) {
    private var handle: EventHandle? = null
    private var isRegistered = false

    @Suppress("UNCHECKED_CAST")
    fun register(): Event<T> {
        println("🔧 Attempting to register event: $name")
        if (isRegistered) return this

        val factory = Events.getFactory<T>(name)
        handle = factory?.invoke(callback) ?: EventHandle(registerDefault(callback))
        isRegistered = true

        return this
    }

    fun unregister(): Event<T> {
        if (!isRegistered) return this

        handle?.unregister()
        isRegistered = false

        return this
    }

    fun isRegistered(): Boolean = isRegistered

    private fun registerDefault(cb: Callback<T>): () -> Unit {
        EventBus.on<T>(name, cb)
        return { EventBus.remove(name, cb) }
    }
}

class EventHandle(private val unregisterFn: () -> Unit) {
    fun unregister() = unregisterFn()
}

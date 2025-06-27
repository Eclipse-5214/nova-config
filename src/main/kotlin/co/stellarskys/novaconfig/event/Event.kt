package co.stellarskys.novaconfig.event

class Event<T> {
    private val listeners = mutableListOf<(T) -> Unit>()

    fun register(listener: (T) -> Unit) {
        listeners += listener
    }

    fun unregister(listener: (T) -> Unit) {
        listeners -= listener
    }

    fun fire(data: T) {
        for (listener in listeners) {
            listener(data)
        }
    }
}
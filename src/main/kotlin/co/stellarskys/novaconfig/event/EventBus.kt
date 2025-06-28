package co.stellarskys.novaconfig.event

object EventBus {
    private val listeners = mutableMapOf<String, MutableList<(Any?) -> Unit>>()

    fun <T : Any> on(name: String, cb: Callback<T>) {
        val list = listeners.getOrPut(name) { mutableListOf() }
        list += cb as (Any?) -> Unit
    }

    fun <T : Any> remove(name: String, cb: Callback<T>) {
        listeners[name]?.remove(cb as (Any?) -> Unit)
    }

    fun post(name: String, arg: Any?) {
        listeners[name]?.forEach { it(arg) }
    }

    fun create(name: String) {
        if (name !in listeners) listeners[name] = mutableListOf()
    }
}

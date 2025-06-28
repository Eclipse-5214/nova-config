package co.stellarskys.novaconfig.event

class Feature(val configID: String) {
    var enabled = false
    val boundListeners = mutableListOf<Event<*>>() // track listener objects

    fun isEnabled(): Boolean = enabled

    /**
     * Registers a named event. Will auto-register/unregister when feature toggles.
     */
    inline fun <reified T : Any> on(eventName: String, noinline callback: (T) -> Unit) {
        val listener = Event(eventName, callback = callback)
        if (enabled) listener.register()
        boundListeners += listener
    }

    internal fun _register() {
        println("✅ [Feature] Enabled: $configID")
        boundListeners.forEach { it.register() }
    }

    internal fun _unregister() {
        println("❌ [Feature] Disabled: $configID")
        boundListeners.forEach { it.unregister() }
    }
}

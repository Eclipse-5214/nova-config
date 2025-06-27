package co.stellarskys.novaconfig.event

class Feature (val configID: String){
    private var enabled = false
    private val boundListeners = mutableListOf<() -> Unit>()

    fun setEnabled(boolean: Boolean) {
        enabled = boolean
    }

    fun isEnabled(): Boolean = enabled

    /**
     * Registers a callback to a named event, and automatically tracks it for unregistration
     */
    fun <T : Any> register(eventName: String, callback: (T) -> Unit) {
        val event = EventBus.getEvent<T>(eventName)
            ?: error("Event '$eventName' does not exist")

        event.register(callback)

        boundListeners += {
            event.unregister(callback)
        }

        // Optionally call immediately if already enabled
        if (enabled) callback as? ((Any) -> Unit) // No fire, just readiness
    }

    internal fun _register() {
        println("✅ [Feature] Enabled: $configID")
        // Hook into events or tick logic
    }

    internal fun _unregister() {
        println("❌ [Feature] Disabled: $configID")
        // Clean up or unregister logic
    }
}
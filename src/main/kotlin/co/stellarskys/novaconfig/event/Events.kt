package co.stellarskys.novaconfig.event

object Events {
    private val factories = mutableMapOf<String, CustomCallback<Any>>()

    fun <T : Any> createEvent(name: String, factory: CustomCallback<T>) {
        @Suppress("UNCHECKED_CAST")
        factories[name.lowercase()] = factory as CustomCallback<Any>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getFactory(name: String): CustomCallback<T>? {
        return factories[name.lowercase()] as? CustomCallback<T>
    }
}

package co.eclipse5214.novaconfig.model

open class ConfigElement(
    val id: String?,
    open var value: Any? = null // default to null unless overridden
)
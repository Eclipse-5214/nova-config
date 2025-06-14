package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.model.elements.Toggle

class ToggleBuilder {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String
    var default: Boolean = false

    fun build(): Toggle {
        return Toggle(
            configName = configName,
            name = name,
            description = description,
            default = default,
        )
    }
}
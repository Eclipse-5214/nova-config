package co.eclipse5214.novaconfig.model.elements

import co.eclipse5214.novaconfig.model.ConfigElement

data class Toggle(
    val configName: String,
    val name: String,
    val description: String,
    val default: Boolean? = false
) : ConfigElement(
    id = configName,
    value = default
)
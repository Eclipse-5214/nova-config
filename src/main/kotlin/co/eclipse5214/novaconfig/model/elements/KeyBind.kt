package co.eclipse5214.novaconfig.model.elements

import co.eclipse5214.novaconfig.model.ConfigElement

data class Keybind(
    val configName: String,
    val name: String,
    val description: String,
    val default: Int
) : ConfigElement(
    id = configName,
    value = default
)

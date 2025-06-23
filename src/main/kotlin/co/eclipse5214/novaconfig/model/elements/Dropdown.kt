package co.eclipse5214.novaconfig.model.elements

import co.eclipse5214.novaconfig.model.ConfigElement

data class Dropdown(
    val configName: String,
    val name: String,
    val description: String,
    val options: List<String>,
    val default: Int = 0
) : ConfigElement(
    id = configName,
    value = default
)

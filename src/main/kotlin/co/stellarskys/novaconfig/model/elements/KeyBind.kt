package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.model.ConfigElement

data class Keybind(
    val configName: String,
    val name: String,
    val description: String,
    val default: Int
) : ConfigElement(
    id = configName,
    value = default
)

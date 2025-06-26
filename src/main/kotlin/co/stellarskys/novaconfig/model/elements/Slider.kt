package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.model.ConfigElement

data class Slider(
    val configName: String,
    val name: String,
    val description: String,
    val min: Float,
    val max: Float,
    val default: Float
) : ConfigElement(
    id = configName,
    value = default
)
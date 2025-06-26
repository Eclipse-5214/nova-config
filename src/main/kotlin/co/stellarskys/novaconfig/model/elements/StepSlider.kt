package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.model.ConfigElement

data class StepSlider(
    val configName: String,
    val name: String,
    val description: String,
    val min: Int,
    val max: Int,
    val default: Int,
    val step: Int
) : ConfigElement(
    id = configName,
    value = default
)
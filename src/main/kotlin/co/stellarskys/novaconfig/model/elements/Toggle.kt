package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.model.ConfigElement

data class Toggle(
    val configName: String,
    val name: String,
    val description: String,
    val default: Boolean? = false
) : ConfigElement(
    id = configName,
    value = default
)
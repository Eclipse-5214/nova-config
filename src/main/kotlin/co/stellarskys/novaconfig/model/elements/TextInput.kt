package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.model.ConfigElement

data class TextInput(
    val configName: String,
    val name: String,
    val description: String,
    val placeholder: String = "",
    var onValueChanged: ((String) -> Unit)? = null
) : ConfigElement(
    id = configName,
    value = placeholder
)
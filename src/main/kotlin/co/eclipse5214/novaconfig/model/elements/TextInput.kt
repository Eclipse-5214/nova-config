package co.eclipse5214.novaconfig.model.elements

import co.eclipse5214.novaconfig.model.ConfigElement

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
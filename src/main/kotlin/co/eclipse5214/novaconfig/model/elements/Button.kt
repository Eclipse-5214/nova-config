package co.eclipse5214.novaconfig.model.elements

import co.eclipse5214.novaconfig.model.ConfigElement

data class Button(
    val configName: String,
    val name: String,
    val description: String,
    val placeholder: String = "Click",
    var onClick: (() -> Unit)? = null
) : ConfigElement(
    id = configName,
)
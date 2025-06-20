package co.eclipse5214.novaconfig.model.elements

import co.eclipse5214.novaconfig.model.ConfigElement

data class TextParagraph(
    val configName: String,
    val name: String,
    val description: String,
) : ConfigElement(
    id = configName
)
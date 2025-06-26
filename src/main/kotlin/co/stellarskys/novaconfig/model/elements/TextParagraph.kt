package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.model.ConfigElement

data class TextParagraph(
    val configName: String,
    val name: String,
    val description: String,
) : ConfigElement(
    id = configName
)
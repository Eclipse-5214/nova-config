package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.builder.ElementBuilder
import co.eclipse5214.novaconfig.model.elements.TextParagraph

class TextParagraphBuilder: ElementBuilder() {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String

    fun build(): TextParagraph {
        return TextParagraph(
            configName = configName,
            name = name,
            description = description,
        ).also { it.shouldShow = shouldShow }
    }
}
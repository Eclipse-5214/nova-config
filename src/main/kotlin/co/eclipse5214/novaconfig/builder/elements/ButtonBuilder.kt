package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.builder.ElementBuilder
import co.eclipse5214.novaconfig.model.elements.Button

class ButtonBuilder: ElementBuilder() {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String
    var placeholder = "Click"
    var onClick: (() -> Unit)? = null

    fun onclick(handler: () -> Unit) {
        onClick = handler
    }

    fun build(): Button {
        return Button(
            configName = configName,
            name = name,
            description = description,
            placeholder = placeholder,
            onClick = onClick
        ).also { it.shouldShow = shouldShow }
    }
}
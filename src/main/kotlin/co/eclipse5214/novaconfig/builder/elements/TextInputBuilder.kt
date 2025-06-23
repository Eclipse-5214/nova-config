package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.builder.ElementBuilder
import co.eclipse5214.novaconfig.model.elements.TextInput

class TextInputBuilder : ElementBuilder() {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String
    var placeholder: String = ""
    var onValueChanged: ((String) -> Unit)? = null

    fun onvaluechange(listener: (String) -> Unit) {
        onValueChanged = listener
    }

    fun build(): TextInput {
        return TextInput(
            configName = configName,
            name = name,
            description = description,
            placeholder = placeholder,
            onValueChanged = onValueChanged
        ).also { it.shouldShow = shouldShow }
    }
}

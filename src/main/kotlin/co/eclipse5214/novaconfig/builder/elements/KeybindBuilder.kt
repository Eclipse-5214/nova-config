package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.builder.ElementBuilder
import co.eclipse5214.novaconfig.model.elements.Keybind

class KeybindBuilder : ElementBuilder() {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String
    var default: Int = 0

    fun build(): Keybind {
        return Keybind(
            configName = configName,
            name = name,
            description = description,
            default = default
        ).also { it.shouldShow = shouldShow }
    }
}

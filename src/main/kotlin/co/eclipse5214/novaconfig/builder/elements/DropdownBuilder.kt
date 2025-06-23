package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.builder.ElementBuilder
import co.eclipse5214.novaconfig.model.elements.Dropdown

class DropdownBuilder : ElementBuilder() {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String
    var options: List<String> = emptyList()
    var default: Int = 0

    fun build(): Dropdown {
        return Dropdown(
            configName = configName,
            name = name,
            description = description,
            options = options,
            default = default
        ).also { it.shouldShow = shouldShow }
    }
}

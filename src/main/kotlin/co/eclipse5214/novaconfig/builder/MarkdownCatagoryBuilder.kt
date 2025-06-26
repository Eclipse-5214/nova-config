package co.eclipse5214.novaconfig.builder

import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.model.ConfigElement

class MarkdownCatagoryBuilder(private val name: String, private val path: String) {
    val elements = mutableListOf<ConfigElement>()

    fun build(): ConfigCategory {
        elements += ConfigElement(id = null)
        return ConfigCategory(name, elements, true)
    }
}
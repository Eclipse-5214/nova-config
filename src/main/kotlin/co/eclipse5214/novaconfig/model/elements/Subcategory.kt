package co.eclipse5214.novaconfig.model.elements

import co.eclipse5214.novaconfig.model.ConfigElement

data class Subcategory(
    val configName: String,
    val name: String,
) : ConfigElement(
    id = configName
)
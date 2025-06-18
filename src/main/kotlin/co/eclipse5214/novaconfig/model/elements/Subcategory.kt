package co.eclipse5214.novaconfig.model.elements

import co.eclipse5214.novaconfig.model.ConfigElement

data class Subcategory(
    val name: String
) : ConfigElement(
    id = null // Allow optional ID
)

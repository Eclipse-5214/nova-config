package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.model.ConfigElement

data class Subcategory(
    val name: String
) : ConfigElement(
    id = null // Allow optional ID
)

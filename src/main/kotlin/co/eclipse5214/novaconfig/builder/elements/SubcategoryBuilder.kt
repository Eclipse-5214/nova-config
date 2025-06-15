package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.model.elements.Subcategory

class SubcategoryBuilder {
    lateinit var configName: String
    lateinit var name: String

    fun build(): Subcategory {
        return Subcategory (
            configName = configName,
            name = name,
        )
    }
}
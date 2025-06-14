package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.model.elements.Subcatagory

class SubcatagoryBuilder {
    lateinit var configName: String
    lateinit var name: String

    fun build(): Subcatagory {
        return Subcatagory(
            configName = configName,
            name = name,
        )
    }
}
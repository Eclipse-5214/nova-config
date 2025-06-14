package co.eclipse5214.novaconfig.builder

import co.eclipse5214.novaconfig.builder.elements.SubcatagoryBuilder
import co.eclipse5214.novaconfig.builder.elements.TextParagraphBuilder
import co.eclipse5214.novaconfig.model.ConfigElement
import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.builder.elements.ToggleBuilder

class CategoryBuilder(private val name: String) {
    val elements = mutableListOf<ConfigElement>()

    fun subcatagory(builder: SubcatagoryBuilder.() -> Unit) {
        val subcatagory = SubcatagoryBuilder().apply(builder).build()
        elements += subcatagory
    }

    fun textparagraph(builder: TextParagraphBuilder.() -> Unit) {
        val textBuilder = TextParagraphBuilder().apply( builder).build()
    }

    fun toggle(builder: ToggleBuilder.() -> Unit) {
        val toggle = ToggleBuilder().apply(builder).build()
        elements += toggle
    }

    // Add more like:
    // fun paragraph { ... }
    // fun subcategory { ... }

    fun build(): ConfigCategory {
        return ConfigCategory(name, elements)
    }
}
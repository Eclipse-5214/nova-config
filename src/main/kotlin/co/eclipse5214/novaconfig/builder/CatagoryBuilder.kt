package co.eclipse5214.novaconfig.builder

import co.eclipse5214.novaconfig.builder.elements.SubcategoryBuilder
import co.eclipse5214.novaconfig.builder.elements.TextParagraphBuilder
import co.eclipse5214.novaconfig.model.ConfigElement
import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.builder.elements.ToggleBuilder
import kotlin.collections.plusAssign

class CategoryBuilder(private val name: String) {
    val elements = mutableListOf<ConfigElement>()

    fun subcategory(builder: SubcategoryBuilder.() -> Unit) {
        val subcategory = SubcategoryBuilder().apply(builder).build()
        elements += subcategory
    }

    fun textparagraph(builder: TextParagraphBuilder.() -> Unit) {
        val textparagraph = TextParagraphBuilder().apply( builder).build()
        elements += textparagraph
    }

    fun toggle(builder: ToggleBuilder.() -> Unit) {
        val toggle = ToggleBuilder().apply(builder).build()
        elements += toggle
    }

    fun build(): ConfigCategory {
        return ConfigCategory(name, elements)
    }
}
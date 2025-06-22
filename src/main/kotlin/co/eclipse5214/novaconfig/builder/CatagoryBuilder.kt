package co.eclipse5214.novaconfig.builder

import co.eclipse5214.novaconfig.builder.elements.ColorPickerBuilder
import co.eclipse5214.novaconfig.builder.elements.TextParagraphBuilder
import co.eclipse5214.novaconfig.model.ConfigElement
import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.builder.elements.ToggleBuilder
import co.eclipse5214.novaconfig.model.elements.Subcategory
import kotlin.collections.plusAssign

class CategoryBuilder(private val name: String) {
    val elements = mutableListOf<ConfigElement>()

    fun subcategory(name: String) {
        elements += Subcategory(name = name)
    }

    fun textparagraph(builder: TextParagraphBuilder.() -> Unit) {
        val textparagraph = TextParagraphBuilder().apply( builder).build()
        elements += textparagraph
    }

    fun toggle(builder: ToggleBuilder.() -> Unit) {
        val toggle = ToggleBuilder().apply(builder).build()
        elements += toggle
    }

    fun colorpicker(builder: ColorPickerBuilder.() -> Unit) {
        val colorpicker = ColorPickerBuilder().apply( builder).build()
        elements += colorpicker
    }

    fun build(): ConfigCategory {
        return ConfigCategory(name, elements)
    }
}
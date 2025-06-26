package co.eclipse5214.novaconfig.builder

import co.eclipse5214.novaconfig.builder.elements.ButtonBuilder
import co.eclipse5214.novaconfig.builder.elements.ColorPickerBuilder
import co.eclipse5214.novaconfig.builder.elements.DropdownBuilder
import co.eclipse5214.novaconfig.builder.elements.KeybindBuilder
import co.eclipse5214.novaconfig.builder.elements.SliderBuilder
import co.eclipse5214.novaconfig.builder.elements.StepSliderBuilder
import co.eclipse5214.novaconfig.builder.elements.TextInputBuilder
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
        val textparagraph = TextParagraphBuilder().apply(builder).build()
        elements += textparagraph
    }

    fun toggle(builder: ToggleBuilder.() -> Unit) {
        val toggle = ToggleBuilder().apply(builder).build()
        elements += toggle
    }

    fun colorpicker(builder: ColorPickerBuilder.() -> Unit) {
        val colorpicker = ColorPickerBuilder().apply(builder).build()
        elements += colorpicker
    }

    fun button(builder: ButtonBuilder.() -> Unit) {
        val button = ButtonBuilder().apply(builder).build()
        elements += button
    }

    fun textinput(builder: TextInputBuilder.() -> Unit) {
        val textinput = TextInputBuilder().apply(builder).build()
        elements += textinput
    }

    fun keybind(builder: KeybindBuilder.() -> Unit) {
        val keybind = KeybindBuilder().apply(builder).build()
        elements += keybind
    }

    fun dropdown(builder: DropdownBuilder.() -> Unit) {
        val dropdown = DropdownBuilder().apply(builder).build()
        elements += dropdown
    }

    fun slider(builder: SliderBuilder.() -> Unit) {
        val slider = SliderBuilder().apply(builder).build()
        elements += slider
    }

    fun stepslider(builder: StepSliderBuilder.() -> Unit) {
        val stepslider = StepSliderBuilder().apply(builder).build()
        elements += stepslider
    }

    fun build(): ConfigCategory {
        return ConfigCategory(name, elements, false)
    }
}
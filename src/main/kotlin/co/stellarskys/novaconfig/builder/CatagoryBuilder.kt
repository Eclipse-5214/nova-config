package co.stellarskys.novaconfig.builder

import co.stellarskys.novaconfig.builder.elements.*
import co.stellarskys.novaconfig.model.ConfigElement
import co.stellarskys.novaconfig.model.ConfigCategory
import co.stellarskys.novaconfig.model.elements.Subcategory

/**
 * DSL builder class for constructing a single configuration category
 * containing interactive elements like toggles, sliders, inputs, and more.
 *
 * Used internally by NovaConfig's [ConfigBuilder].
 *
 * @param name The display name of this category tab.
 */
class CategoryBuilder(private val name: String) {

    /**
     * A list of config elements (toggles, sliders, etc.) and subcategories
     * that will be rendered within this category.
     */
    val elements = mutableListOf<ConfigElement>()

    /**
     * Adds a non-interactive subheading to group related elements visually.
     *
     * @param name The label displayed for the subcategory section.
     */
    fun subcategory(name: String) {
        elements += Subcategory(name = name)
    }

    /**
     * Adds a text paragraph element, useful for explanatory content.
     *
     * @param builder A DSL block to configure the paragraph element.
     */
    fun textparagraph(builder: TextParagraphBuilder.() -> Unit) {
        val textparagraph = TextParagraphBuilder().apply(builder).build()
        elements += textparagraph
    }

    /**
     * Adds a toggle element (true/false).
     *
     * @param builder A DSL block to configure the toggle.
     */
    fun toggle(builder: ToggleBuilder.() -> Unit) {
        val toggle = ToggleBuilder().apply(builder).build()
        elements += toggle
    }

    /**
     * Adds a color picker element for selecting an RGBA value.
     *
     * @param builder A DSL block to configure the color picker.
     */
    fun colorpicker(builder: ColorPickerBuilder.() -> Unit) {
        val colorpicker = ColorPickerBuilder().apply(builder).build()
        elements += colorpicker
    }

    /**
     * Adds a clickable button element.
     *
     * @param builder A DSL block to configure the button.
     */
    fun button(builder: ButtonBuilder.() -> Unit) {
        val button = ButtonBuilder().apply(builder).build()
        elements += button
    }

    /**
     * Adds a text input box element.
     *
     * @param builder A DSL block to configure the text input.
     */
    fun textinput(builder: TextInputBuilder.() -> Unit) {
        val textinput = TextInputBuilder().apply(builder).build()
        elements += textinput
    }

    /**
     * Adds a keybind input element (expects a key code).
     *
     * @param builder A DSL block to configure the keybind.
     */
    fun keybind(builder: KeybindBuilder.() -> Unit) {
        val keybind = KeybindBuilder().apply(builder).build()
        elements += keybind
    }

    /**
     * Adds a dropdown selection element.
     *
     * @param builder A DSL block to configure the dropdown.
     */
    fun dropdown(builder: DropdownBuilder.() -> Unit) {
        val dropdown = DropdownBuilder().apply(builder).build()
        elements += dropdown
    }

    /**
     * Adds a continuous (float) slider element.
     *
     * @param builder A DSL block to configure the slider.
     */
    fun slider(builder: SliderBuilder.() -> Unit) {
        val slider = SliderBuilder().apply(builder).build()
        elements += slider
    }

    /**
     * Adds a stepped slider element (discrete values).
     *
     * @param builder A DSL block to configure the step slider.
     */
    fun stepslider(builder: StepSliderBuilder.() -> Unit) {
        val stepslider = StepSliderBuilder().apply(builder).build()
        elements += stepslider
    }

    /**
     * Builds and returns a complete [ConfigCategory] instance
     * containing all registered UI elements.
     *
     * @return A constructed config category ready for registration.
     */
    fun build(): ConfigCategory {
        return ConfigCategory(name, elements, false)
    }
}

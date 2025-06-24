package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.model.elements.Button
import co.eclipse5214.novaconfig.model.elements.ColorPicker
import co.eclipse5214.novaconfig.model.elements.Dropdown
import co.eclipse5214.novaconfig.model.elements.Keybind
import co.eclipse5214.novaconfig.model.elements.Slider
import co.eclipse5214.novaconfig.model.elements.StepSlider
import co.eclipse5214.novaconfig.model.elements.Subcategory
import co.eclipse5214.novaconfig.model.elements.TextInput
import co.eclipse5214.novaconfig.model.elements.TextParagraph
import co.eclipse5214.novaconfig.model.elements.Toggle
import co.eclipse5214.novaconfig.ui.elements.ButtonUIBuilder
import co.eclipse5214.novaconfig.ui.elements.ColorPickerUIBuilder
import co.eclipse5214.novaconfig.ui.elements.DropdownUIBuilder
import co.eclipse5214.novaconfig.ui.elements.KeybindUIBuilder
import co.eclipse5214.novaconfig.ui.elements.SliderUIBuilder
import co.eclipse5214.novaconfig.ui.elements.StepSliderUIBuilder
import co.eclipse5214.novaconfig.ui.elements.SubcategoryUIBuilder
import co.eclipse5214.novaconfig.ui.elements.TextInputUIBuilder
import co.eclipse5214.novaconfig.ui.elements.TextParagraphUIBuilder
import co.eclipse5214.novaconfig.ui.elements.ToggleUIBuilder
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*

class CategoryUIBuilder {

    /**
     * Initializes and renders a scrollable container for a specific configuration category.
     *
     * This function sets up a bounded `ScrollComponent`, attaches it to the root,
     * and populates it with all visible elements from the given category using `drawElements(...)`.
     *
     * @param root The parent UIComponent to which the scrollable category view will be attached.
     * @param config The full configuration model containing all categories and their current values.
     * @param category The specific category whose UI elements should be rendered into the container.
     */
    fun build(root: UIComponent, config: Config, category: ConfigCategory) {
        val catagoryContainer = ScrollComponent()
            .constrain {
                width = 450.pixels()
                height = 325.pixels()
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setChildOf(root)

        drawElements(catagoryContainer, config, category)
    }

    // Tracks rendered UI elements and their original index to support partial redraw
    private val rendered = mutableListOf<Pair<Int, UIComponent>>()

    /**
     * Renders visible configuration elements into the given UI root.
     *
     * This function selectively redraws only the elements starting from the specified index,
     * clearing previously rendered components beyond that point while preserving scroll order
     * and layout continuity. It evaluates visibility using the `shouldShow(...)` predicate
     * per element, based on the current config state.
     *
     * @param root The UIComponent (typically a ScrollComponent's container) that will host the drawn elements.
     * @param config The overall Config object containing all categories and their elements.
     * @param category The specific ConfigCategory whose elements are to be rendered.
     * @param startingIndex Optional index in the element list to begin drawing from; defaults to 0 for full layout.
     */
    private fun drawElements(root: UIComponent, config: Config, category: ConfigCategory, startingIndex: Int = 0) {

        // Remove all components rendered after the starting index to prep for partial redraw
        val toRemove = rendered.dropWhile { it.first < startingIndex }
        toRemove.forEach { it.second.parent.removeChild(it.second) }
        rendered.removeAll(toRemove)

        // Dummy settings map used to evaluate element visibility via shouldShow
        val settings = object : Map<String, Any?> {
            override fun get(key: String): Any? = category.elements.find { it.id == key }?.value
            override val entries get() = emptySet<Map.Entry<String, Any?>>()
            override val keys get() = emptySet<String>()
            override val values get() = emptyList<Any?>()
            override val size get() = 0
            override fun isEmpty() = false
            override fun containsKey(key: String) = true
            override fun containsValue(value: Any?) = false
        }

        // Iterate and render each visible UI element in the category
        category.elements.forEachIndexed { index, element ->
            if (index < startingIndex || !element.shouldShow(settings)) return@forEachIndexed

            val component = when (element) {
                is Button       -> ButtonUIBuilder().build(root, element)
                is ColorPicker  -> ColorPickerUIBuilder().build(root, element)
                is Dropdown     -> DropdownUIBuilder().build(root, element)
                is Keybind      -> KeybindUIBuilder().build(root, element)
                is Slider       -> SliderUIBuilder().build(root, element)
                is StepSlider   -> StepSliderUIBuilder().build(root, element)
                is Subcategory  -> SubcategoryUIBuilder().build(root, element)
                is TextInput    -> TextInputUIBuilder().build(root, element)
                is TextParagraph-> TextParagraphUIBuilder().build(root, element)
                is Toggle       -> ToggleUIBuilder().build(root, element) {
                    // Re-render nested elements when toggle state changes
                    drawElements(root, config, category, index + 1)
                }
                else -> null // Unknown element type
            }

            // Position each component vertically in stack
            component?.constrain {
                x = CenterConstraint()
                y = SiblingConstraint(5f)
            }

            // Track rendered component for future redraw
            component?.let {
                rendered.add(index to it)
            }
        }
    }
}

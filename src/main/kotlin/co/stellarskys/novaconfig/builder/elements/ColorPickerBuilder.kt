package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.ColorPicker
import co.stellarskys.novaconfig.model.elements.RGBA

/**
 * DSL builder for creating a color picker element within a NovaConfig category.
 *
 * The color picker allows users to select colors using RGBA components.
 * It supports compact or expanded UI modes and integrates seamlessly
 * with NovaConfig’s animation, theming, and color validation systems.
 *
 * Example:
 * ```
 * colorpicker {
 *     configName = "accent_color"
 *     name = "UI Accent"
 *     description = "Choose your accent color"
 *     default = rgba(120, 200, 255, 255)
 * }
 * ```
 */
class ColorPickerBuilder : ElementBuilder() {

    /**
     * Unique identifier for this color picker in the config backend.
     */
    lateinit var configName: String

    /**
     * The visible label displayed in the color picker UI.
     */
    lateinit var name: String

    /**
     * Additional description or supporting text shown in the UI.
     */
    lateinit var description: String

    /**
     * The default color selected when the config is first loaded or reset.
     * Defaults to opaque white (255, 255, 255, 255).
     */
    var default: RGBA = RGBA(255, 255, 255, 255)

    /**
     * Helper function to construct an [RGBA] color instance.
     * Useful for keeping the DSL concise and expressive.
     *
     * @param r Red component (0–255)
     * @param g Green component (0–255)
     * @param b Blue component (0–255)
     * @param a Alpha component (0–255), defaults to 255 (fully opaque)
     * @return An [RGBA] object representing the color
     */
    fun rgba(r: Int, g: Int, b: Int, a: Int = 255): RGBA = RGBA(r, g, b, a)

    /**
     * Builds the [ColorPicker] element from the current builder state.
     * Applies the inherited [shouldShow] predicate for conditional rendering.
     *
     * @return A fully constructed [ColorPicker] model element
     */
    fun build(): ColorPicker {
        return ColorPicker(
            configName = configName,
            name = name,
            description = description,
            default = default,
        ).also { it.shouldShow = shouldShow }
    }
}
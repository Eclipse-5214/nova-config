package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.Slider

/**
 * DSL builder for defining a continuous slider element in a NovaConfig category.
 *
 * Sliders allow users to select a floating-point value within a defined range.
 * They're great for settings like volume, speed, scale, or anything with fine-grained control.
 *
 * Example:
 * ```
 * slider {
 *     configName = "music_volume"
 *     name = "Music Volume"
 *     description = "Adjust background music volume"
 *     min = 0f
 *     max = 1f
 *     default = 0.5f
 * }
 * ```
 */
class SliderBuilder : ElementBuilder() {

    /**
     * Unique identifier used internally to reference this slider’s value in the config.
     */
    lateinit var configName: String

    /**
     * The visible label displayed in the slider UI.
     */
    lateinit var name: String

    /**
     * Additional descriptive text displayed below the slider.
     */
    lateinit var description: String

    /**
     * The minimum value this slider can represent.
     * Defaults to 0.0f.
     */
    var min: Float = 0f

    /**
     * The maximum value this slider can represent.
     * Defaults to 100.0f.
     */
    var max: Float = 100f

    /**
     * The default value when the config is first created or reset.
     * Must fall within [min] and [max].
     */
    var default: Float = 0f

    /**
     * Builds the [Slider] model instance based on the current builder state.
     * Applies the inherited [shouldShow] predicate from [ElementBuilder] to control visibility.
     *
     * @return A configured [Slider] element ready to be added to a category.
     */
    fun build(): Slider {
        return Slider(
            configName = configName,
            name = name,
            description = description,
            min = min,
            max = max,
            default = default
        ).also { it.shouldShow = shouldShow }
    }
}

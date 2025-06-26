package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.StepSlider

/**
 * DSL builder for defining a step-based slider element in a NovaConfig category.
 *
 * Step sliders are used for selecting a value in a fixed range using discrete intervals.
 * Ideal for numeric options where only specific values make sense (e.g., difficulty levels, tick intervals).
 *
 * Example:
 * ```
 * stepslider {
 *     configName = "chunk_radius"
 *     name = "Chunk Load Radius"
 *     description = "Number of chunks around player to remain active"
 *     min = 1
 *     max = 5
 *     step = 1
 *     default = 3
 * }
 * ```
 */
class StepSliderBuilder : ElementBuilder() {

    /**
     * Unique identifier used to track this element’s value in the config system.
     */
    lateinit var configName: String

    /**
     * The visible label displayed in the step slider UI.
     */
    lateinit var name: String

    /**
     * A short explanation or contextual message shown below the slider.
     */
    lateinit var description: String

    /**
     * The smallest selectable value (inclusive).
     * Defaults to 0.
     */
    var min: Int = 0

    /**
     * The largest selectable value (inclusive).
     * Defaults to 10.
     */
    var max: Int = 10

    /**
     * The value selected by default when no saved config is available.
     * Must align with the step interval.
     */
    var default: Int = 0

    /**
     * The distance between each selectable tick on the slider.
     * Must be ≥ 1. Defaults to 1.
     */
    var step: Int = 1

    /**
     * Builds and returns the final [StepSlider] config element from the current builder state.
     * Enforces that [step] is ≥ 1 and applies [shouldShow] for conditional rendering.
     *
     * @return A configured [StepSlider] element.
     */
    fun build(): StepSlider {
        require(step >= 1) { "Step must be at least 1" }
        return StepSlider(
            configName = configName,
            name = name,
            description = description,
            min = min,
            max = max,
            default = default,
            step = step
        ).also { it.shouldShow = shouldShow }
    }
}
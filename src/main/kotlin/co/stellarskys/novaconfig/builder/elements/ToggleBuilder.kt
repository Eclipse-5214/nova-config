package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.Toggle

/**
 * DSL builder for defining a toggle (boolean switch) element in a NovaConfig category.
 *
 * Toggles allow players to enable or disable features and represent simple true/false settings.
 * Ideal for flags, debug modes, feature toggles, or accessibility preferences.
 *
 * Example:
 * ```
 * toggle {
 *     configName = "enable_shadows"
 *     name = "Enable Shadows"
 *     description = "Toggle dynamic shadow rendering"
 *     default = true
 * }
 * ```
 */
class ToggleBuilder : ElementBuilder() {

    /**
     * Unique key used to store and retrieve this toggle’s value in the config system.
     */
    lateinit var configName: String

    /**
     * The visible label displayed in the toggle UI.
     */
    lateinit var name: String

    /**
     * Descriptive text providing context or explanation beneath the toggle.
     */
    lateinit var description: String

    /**
     * Whether the toggle is on by default.
     * Defaults to false.
     */
    var default: Boolean = false

    /**
     * Builds and returns the [Toggle] config element from this builder’s data.
     * Applies [shouldShow] for conditional visibility logic.
     *
     * @return A configured [Toggle] element instance.
     */
    fun build(): Toggle {
        return Toggle(
            configName = configName,
            name = name,
            description = description,
            default = default,
        ).also { it.shouldShow = shouldShow }
    }
}
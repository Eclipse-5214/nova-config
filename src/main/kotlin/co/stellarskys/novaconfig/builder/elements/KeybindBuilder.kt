package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.Keybind

/**
 * DSL builder for defining a keybind (keyboard shortcut) element in a NovaConfig category.
 *
 * Keybinds allow players to assign a specific keyboard key to a custom action or feature.
 * The stored value is the raw key code (as an [Int]), which can be used in your mod logic.
 *
 * Example:
 * ```
 * keybind {
 *     configName = "open_menu"
 *     name = "Open Config Menu"
 *     description = "Press this key to open the Nova config screen"
 *     default = 80 // P key
 * }
 * ```
 */
class KeybindBuilder : ElementBuilder() {

    /**
     * Unique identifier used to save this keybind’s value in the config system.
     */
    lateinit var configName: String

    /**
     * The visible label displayed in the keybind UI.
     */
    lateinit var name: String

    /**
     * Descriptive text shown beneath the keybind, explaining its purpose.
     */
    lateinit var description: String

    /**
     * The default key code to use if no config is present.
     * Defaults to 0 (unbound).
     */
    var default: Int = 0

    /**
     * Builds and returns a [Keybind] model instance from this builder's state.
     * Applies the inherited [shouldShow] predicate for conditional rendering in the UI.
     *
     * @return A configured [Keybind] element ready to be added to a config.
     */
    fun build(): Keybind {
        return Keybind(
            configName = configName,
            name = name,
            description = description,
            default = default
        ).also { it.shouldShow = shouldShow }
    }
}
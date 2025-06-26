package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.Dropdown

/**
 * DSL builder for creating a dropdown (combo box) element in a NovaConfig category.
 *
 * Dropdowns allow users to select a single option from a predefined list of strings.
 * The selected index is saved and can be used in conditional visibility or mod logic.
 *
 * Example:
 * ```
 * dropdown {
 *     configName = "theme_mode"
 *     name = "Theme"
 *     description = "Choose your UI theme"
 *     options = listOf("Nova", "Dark", "Void")
 *     default = 0
 * }
 * ```
 */
class DropdownBuilder : ElementBuilder() {

    /**
     * Unique identifier for this dropdown in the config system.
     */
    lateinit var configName: String

    /**
     * The visible label displayed in the dropdown UI.
     */
    lateinit var name: String

    /**
     * Additional description or supporting text shown in the UI.
     */
    lateinit var description: String

    /**
     * The list of string options available to choose from.
     */
    var options: List<String> = emptyList()

    /**
     * The default selected index when the config is first loaded or reset.
     * Defaults to 0 (first item in the list).
     */
    var default: Int = 0

    /**
     * Builds and returns a [Dropdown] model element from this builder.
     * Applies the inherited [shouldShow] predicate for conditional rendering.
     *
     * @return A configured [Dropdown] element instance.
     */
    fun build(): Dropdown {
        return Dropdown(
            configName = configName,
            name = name,
            description = description,
            options = options,
            default = default
        ).also { it.shouldShow = shouldShow }
    }
}
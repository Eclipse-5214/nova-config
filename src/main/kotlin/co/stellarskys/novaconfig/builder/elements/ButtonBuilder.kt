package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.Button

/**
 * DSL builder for creating a clickable button element in a NovaConfig category.
 *
 * Buttons can be used to trigger actions, open links, or display feedback messages
 * without modifying config state.
 *
 * Example usage:
 * ```
 * button {
 *     configName = "trigger_action"
 *     name = "Press Me"
 *     description = "This button does something cool"
 *     onclick {
 *         chatutils.clientMsg("§a[Nova] You clicked the button!", false)
 *     }
 * }
 * ```
 */
class ButtonBuilder : ElementBuilder() {

    /**
     * The unique ID of this button within the config, used for internal reference.
     */
    lateinit var configName: String

    /**
     * The visible label shown on the button in the UI.
     */
    lateinit var name: String

    /**
     * The tooltip text shown when hovering over the button.
     */
    lateinit var description: String

    /**
     * Optional placeholder text (not currently used visually, but available for future theming).
     * Defaults to `"Click"`.
     */
    var placeholder = "Click"

    /**
     * Optional click handler called when the user clicks the button.
     */
    var onClick: (() -> Unit)? = null

    /**
     * Assigns the function to call when this button is clicked.
     *
     * @param handler The lambda to execute on button click.
     */
    fun onclick(handler: () -> Unit) {
        onClick = handler
    }

    /**
     * Builds and returns a [Button] model instance from the provided values.
     * The [shouldShow] predicate inherited from [ElementBuilder] is applied during rendering.
     *
     * @return A configured [Button] instance.
     */
    fun build(): Button {
        return Button(
            configName = configName,
            name = name,
            description = description,
            placeholder = placeholder,
            onClick = onClick
        ).also { it.shouldShow = shouldShow }
    }
}
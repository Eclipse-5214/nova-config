package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.TextInput

/**
 * DSL builder for defining a text input field in a NovaConfig category.
 *
 * Text inputs allow users to enter arbitrary strings—useful for labels, commands,
 * URLs, or custom values. Input is persisted to the config and may trigger live callbacks.
 *
 * Example:
 * ```
 * textinput {
 *     configName = "player_nickname"
 *     name = "Nickname"
 *     description = "Set your in-game nickname"
 *     placeholder = "Enter nickname..."
 *     onvaluechange { value ->
 *         println("Nickname updated to: $value")
 *     }
 * }
 * ```
 */
class TextInputBuilder : ElementBuilder() {

    /**
     * Unique identifier for this text input’s value in the config system.
     */
    lateinit var configName: String

    /**
     * The visible label displayed in the text input UI.
     */
    lateinit var name: String

    /**
     * Supplementary description shown beneath the text field.
     */
    lateinit var description: String

    /**
     * Faded placeholder shown when the field is empty.
     */
    var placeholder: String = ""

    /**
     * Optional callback triggered when the user modifies the text.
     */
    var onValueChanged: ((String) -> Unit)? = null

    /**
     * Assigns a lambda to run when the input value changes.
     *
     * @param listener A function that receives the updated string.
     */
    fun onvaluechange(listener: (String) -> Unit) {
        onValueChanged = listener
    }

    /**
     * Builds a [TextInput] element based on the current builder values.
     * Applies the [shouldShow] visibility predicate for conditional rendering.
     *
     * @return A configured [TextInput] element instance.
     */
    fun build(): TextInput {
        return TextInput(
            configName = configName,
            name = name,
            description = description,
            placeholder = placeholder,
            onValueChanged = onValueChanged
        ).also { it.shouldShow = shouldShow }
    }
}
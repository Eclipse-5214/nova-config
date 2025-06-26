package co.stellarskys.novaconfig.builder.elements

import co.stellarskys.novaconfig.builder.ElementBuilder
import co.stellarskys.novaconfig.model.elements.TextParagraph

/**
 * DSL builder for creating a text paragraph element in a NovaConfig category.
 *
 * Text paragraphs are static blocks of descriptive content, useful for labeling,
 * explaining adjacent config elements, or narrating a category’s intent.
 * Ideal for onboarding messages, feature descriptions, or fun lore.
 *
 * Example:
 * ```
 * textparagraph {
 *     configName = "intro"
 *     name = "§dWelcome to Nova"
 *     description = "§bThis section explains how each setting works."
 * }
 * ```
 */
class TextParagraphBuilder : ElementBuilder() {

    /**
     * Unique identifier for this element within the config system.
     */
    lateinit var configName: String

    /**
     * The visible label displayed in the paragraph block.
     */
    lateinit var name: String

    /**
     * The body text displayed beneath the label.
     * Supports line breaks (`\\n`) and Minecraft color codes (e.g., §b).
     */
    lateinit var description: String

    /**
     * Builds and returns a [TextParagraph] element from this builder’s state.
     * Applies [shouldShow] to conditionally render based on config state.
     *
     * @return A configured [TextParagraph] element.
     */
    fun build(): TextParagraph {
        return TextParagraph(
            configName = configName,
            name = name,
            description = description,
        ).also { it.shouldShow = shouldShow }
    }
}
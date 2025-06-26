package co.stellarskys.novaconfig.ui.elements

import co.stellarskys.novaconfig.model.elements.TextParagraph
import co.stellarskys.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*

/**
 * Builds a non-interactive paragraph component used for descriptive or instructional text.
 *
 * Typically styled in a secondary or muted tone, this element enhances clarity
 * and breaks up sections of the config interface.
 */

class TextParagraphUIBuilder {

    /**
     * Builds a non-interactive text paragraph component.
     *
     * Typically used to display static descriptive text or section guidance within a configuration screen.
     *
     * @param root The UIComponent to attach the paragraph to.
     * @param textParagraph The TextParagraph model containing the text and formatting.
     * @return A styled UIComponent representing the paragraph.
     */

    fun build(root: UIComponent, textParagraph: TextParagraph): UIComponent {
        val textParagraphContainer = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 85.pixels()
                x = CenterConstraint()
                y = PixelConstraint(20f)
            }
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

        val textParagraphTitle = UIText(textParagraph.name)
            .constrain {
                x = CenterConstraint() // Moves text to the left
                y = PixelConstraint(7f)
            }
            .setTextScale(1.2f.pixels())
            .setChildOf(textParagraphContainer)

        // Split description into lines manually (assuming ~50 char per line)
        val description = UIWrappedText(textParagraph.description, centered = true)
            .constrain {
                x = CenterConstraint()
                y = PixelConstraint(22f)
                width = 400.pixels()
            }
            .setChildOf(textParagraphContainer)

        return textParagraphContainer
    }

    // Custom text wrapping function
    private fun wrapText(text: String, maxCharsPerLine: Int): List<String> {
        val rawLines = text.split("\n") // Split by manual new lines first
        val wrappedLines = mutableListOf<String>()

        rawLines.forEach { rawLine ->
            val words = rawLine.split(" ")
            var currentLine = ""

            words.forEach { word ->
                if ((currentLine.length + word.length) > maxCharsPerLine) {
                    wrappedLines.add(currentLine.trim()) // Add completed line
                    currentLine = word // Start new line
                } else {
                    currentLine += " $word"
                }
            }

            if (currentLine.isNotEmpty()) {
                wrappedLines.add(currentLine.trim()) // Add the final line for this section
            }
        }

        return wrappedLines
    }
}
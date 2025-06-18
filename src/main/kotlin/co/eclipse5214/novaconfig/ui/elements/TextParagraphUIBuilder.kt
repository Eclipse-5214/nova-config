package co.eclipse5214.novaconfig.ui.elements

import co.eclipse5214.novaconfig.model.elements.TextParagraph
import co.eclipse5214.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*


class TextParagraphUIBuilder {
    fun build(root: UIComponent, textParagraph: TextParagraph): UIComponent {
        val textParagraphContainer = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 80.pixels()
                x = CenterConstraint()
                y = CenterConstraint()
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
        val wrappedLines = wrapText(textParagraph.description, 75)

        wrappedLines.forEachIndexed { index, line ->
            UIText("§7$line")
                .constrain {
                    x = CenterConstraint()
                    y = PixelConstraint(22f + (index * 12)) // Spacing between lines
                }
                .setChildOf(textParagraphContainer)
        }

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
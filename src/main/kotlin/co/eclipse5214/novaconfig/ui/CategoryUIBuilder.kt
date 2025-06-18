package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.model.elements.TextParagraph
import co.eclipse5214.novaconfig.model.elements.Toggle
import co.eclipse5214.novaconfig.ui.elements.TextParagraphUIBuilder
import co.eclipse5214.novaconfig.ui.elements.ToggleUIBuilder
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.ScissorEffect
import java.awt.Color

class CategoryUIBuilder {
    fun build(root: UIComponent, category: ConfigCategory) {
        val categoryContainer = UIBlock()
            .constrain {
                width = 450.pixels()
                height = 325.pixels()
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface1)
            .effect(ScissorEffect())
            .setChildOf(root)

        val scroller = ScrollComponent()
            .constrain {
                width = 450.pixels()
                height = 325.pixels()
                x = CenterConstraint()
                y = SiblingConstraint()
            }
            .setChildOf(categoryContainer)

        val title = UIText(category.name)
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint() - 150.pixels()
            }
            .setTextScale(1.2f.pixels())
            .setChildOf(scroller)

        // Iterate through category elements and render toggles
        var yOffset =  20f // Start position
        var previousHeight = 0f // Tracks last element’s height

        category.elements.forEach { element ->
            val elementHeight = when (element) {
                is Toggle -> 60f
                is TextParagraph -> 85f
                else -> 50f // Default fallback
            }

            val uiComponent = when (element) {
                is Toggle -> ToggleUIBuilder().build(scroller, element)
                is TextParagraph -> TextParagraphUIBuilder().build(scroller, element)
                else -> null
            }

            // **Fix: Use previous element's height for spacing consistency**
            yOffset += previousHeight

            uiComponent?.constrain {
                y = yOffset.pixels()
            }

            previousHeight = elementHeight + 5f// Store current height for next iteration
        }

        yOffset += previousHeight

        // this fixes scrolling
        val bottumbuffer = UIBlock()
            .constrain {
                width = 450.pixels()
                height = 10.pixels()
                x = CenterConstraint()
                y = yOffset.pixels()
            }
            .setColor(Color(0,0,0, 0))
            .setChildOf(scroller)
    }
}

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
            .setChildOf(root)

        val title = UIText(category.name)
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint() - 150.pixels()
            }
            .setTextScale(1.2f.pixels())
            .setChildOf(categoryContainer)

        // Iterate through category elements and render toggles
        category.elements.forEachIndexed { index, element ->
            if (element is Toggle) {
                ToggleUIBuilder().build(categoryContainer, element)
                    .constrain {
                        y = CenterConstraint() - 100.pixels() + (index * 65).pixels()
                    }
            }

            if (element is TextParagraph) {
                TextParagraphUIBuilder().build(categoryContainer, element)
                    .constrain {
                        y = CenterConstraint() - 100.pixels() + (index * 85).pixels()
                    }
            }
        }
    }
}

package co.stellarskys.novaconfig.ui.elements

import co.stellarskys.novaconfig.model.elements.Subcategory
import co.stellarskys.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*

/**
 * Renders a visual marker for a subcategory heading within a configuration category.
 *
 * This builder creates a lightweight separator, often used to group related elements
 * and improve layout hierarchy and visual flow.
 */

class SubcategoryUIBuilder {

    /**
     * Builds a visual marker or separator representing a subcategory within a category.
     *
     * This can include a label, line, or indentation element used to group related options under a heading.
     *
     * @param root The UIComponent to attach the subcategory label or marker to.
     * @param subcategory The Subcategory model containing the display name and any metadata.
     * @return A UIComponent that visually separates this section within the scroll layout.
     */

    fun build(root: UIComponent, subcategory: Subcategory): UIComponent {
        val SubcategoryRoot = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 15.pixels()
                x = CenterConstraint()
                y = SiblingConstraint()
            }
            .setColor(NovaPalette.Base)
            .setChildOf(root)

        val SubcategoryUnderline = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 5.pixels()
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface0)
            .setChildOf(SubcategoryRoot)

        val SubcategoryBox = UIRoundedRectangle(6f)
            .constrain {
                width = 50.pixels()
                height = 15.pixels()
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface1)
            .setChildOf(SubcategoryUnderline)


        val textParagraphTitle = UIText(subcategory.name)
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setTextScale(1.2f.pixels())
            .setChildOf(SubcategoryUnderline)

        return SubcategoryRoot
    }
}

package co.eclipse5214.novaconfig.ui.elements

import co.eclipse5214.novaconfig.model.elements.Subcategory
import co.eclipse5214.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*


class SubcategoryUIBuilder {
    fun build(root: UIComponent, subcategory: Subcategory): UIComponent {
        val SubcategoryUnderline = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 5.pixels()
                x = CenterConstraint()
                y = PixelConstraint(20f)
            }
            .setColor(NovaPalette.Mauve)
            .setChildOf(root)

        val textParagraphTitle = UIText(subcategory.name)
            .constrain {
                x = CenterConstraint() // Moves text to the left
                y = PixelConstraint(7f)
            }
            .setTextScale(1.2f.pixels())
            .setChildOf(SubcategoryUnderline)

        return SubcategoryUnderline
    }
}

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
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

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

        return SubcategoryUnderline
    }
}

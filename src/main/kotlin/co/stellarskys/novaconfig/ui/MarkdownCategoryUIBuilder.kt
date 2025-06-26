package co.stellarskys.novaconfig.ui

import co.stellarskys.novaconfig.model.ConfigCategory
import gg.essential.elementa.markdown.MarkdownComponent
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*

class MarkdownCategoryUIBuilder {
    /**
     * Initializes and renders a scrollable container for a specific configuration category.
     *
     * This function sets up a bounded `ScrollComponent`, attaches it to the root,
     * and populates it with all visible elements from the given category using `drawElements(...)`.
     *
     * @param root The parent UIComponent to which the scrollable category view will be attached.
     * @param category The specific category whose UI elements should be rendered into the container.
     */
    fun build(root: UIComponent, category: ConfigCategory) {
        val catagoryContainer = ScrollComponent()
            .constrain {
                width = 450.pixels()
                height = 325.pixels()
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setChildOf(root)

        val markdown = MarkdownComponent(category.path ?: "[Missing path]")
            .constrain {
                width = RelativeConstraint(1f)
                height = RelativeConstraint(1f)
                x = CenterConstraint()
                y = PixelConstraint(2f)
            }
            .setChildOf(catagoryContainer)
    }
}
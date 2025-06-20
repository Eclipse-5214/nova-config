package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.model.elements.Subcategory
import co.eclipse5214.novaconfig.model.elements.TextParagraph
import co.eclipse5214.novaconfig.model.elements.Toggle
import co.eclipse5214.novaconfig.ui.elements.SubcategoryUIBuilder
import co.eclipse5214.novaconfig.ui.elements.TextParagraphUIBuilder
import co.eclipse5214.novaconfig.ui.elements.ToggleUIBuilder
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.ScissorEffect
import java.awt.Color

class CategoryUIBuilder {
    fun build(root: UIComponent, config: Config, category: ConfigCategory) {
        val catagoryContainer = ScrollComponent()
            .constrain {
                width = 450.pixels()
                height = 325.pixels()
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setChildOf(root)

        drawElements(catagoryContainer, config, category)
    }

    private val rendered = mutableListOf<Pair<Int, UIComponent>>()

    private fun drawElements(root: UIComponent, config: Config, category: ConfigCategory) {

        // Remove UI components from scroller
        root.clearChildren()


        val settings = object : Map<String, Any?> {
            override fun get(key: String): Any? = category.elements.find { it.id == key }?.value
            override val entries get() = emptySet<Map.Entry<String, Any?>>()
            override val keys get() = emptySet<String>()
            override val values get() = emptyList<Any?>()
            override val size get() = 0
            override fun isEmpty() = false
            override fun containsKey(key: String) = true
            override fun containsValue(value: Any?) = false
        }

        // Layout tracking
        //var yOffset = 20f
        //var previousHeight = 0f
        var previousComponent: UIComponent? = null

        category.elements.forEachIndexed { offset, element ->
            println("[ShouldShow] ${element.id} => ${element.shouldShow(settings)} (value = ${settings[element.id]})")

            if (!element.shouldShow(settings)) return@forEachIndexed
            println("[DrawElements] rendering ${element.id}")


            val component = when (element) {
                is Toggle -> ToggleUIBuilder().build(root, element) {
                    drawElements(root, config, category)
                }
                is TextParagraph -> TextParagraphUIBuilder().build(root, element)
                is Subcategory -> SubcategoryUIBuilder().build(root, element)
                else -> null
            }

            //component?.constrain { y = yOffset.pixels() }
            component?.constrain {
                x = CenterConstraint()
                y = SiblingConstraint(5f)
            }

            component?.setChildOf(root)
            if (component != null) previousComponent = component

            //previousHeight = height + 5f
        }

        // Scroll buffer
        /*

         */
    }
}

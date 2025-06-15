package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.model.elements.Toggle
import co.eclipse5214.novaconfig.model.elements.TextParagraph
import co.eclipse5214.novaconfig.model.elements.Subcategory
import co.eclipse5214.novaconfig.utils.chatutils
import gg.essential.elementa.components.*
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.*
import gg.essential.elementa.components.input.UITextInput
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.ScissorEffect
import gg.essential.elementa.layoutdsl.Modifier
import gg.essential.elementa.layoutdsl.gradient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text

class ConfigUIBuilder(private val config: Config) {
    fun build(): Screen = object : Screen(Text.of(config.name)) {
        lateinit var root: UIBlock

        override fun init() {
            root = UIBlock(surface0).setSize(fullWidth, fullHeight)

            val titleBar = UIBlock(surface1)
                .setHeight(pixel(25f)).setWidth(relative(1f))
                .setY(pixel(0f))
            titleBar.addChild(
                UIText("⚙ ${config.name}")
                    .setTextColor(overlay0)
                    .setTextScale(1f)
                    .setX(pixel(8f))
                    .setY(center())
            )

            val sidebar = UIBlock(surface1)
                .setX(pixel(0f)).setY(pixel(25f))
                .setWidth(pixel(120f)).setHeight(relative(1f, -25f))

            val mainPanel = UIBlock(surface0)
                .setX(pixel(120f)).setY(pixel(25f))
                .setWidth(relative(1f, -120f)).setHeight(relative(1f, -25f))

            root.addChild(titleBar)
            root.addChild(sidebar)
            root.addChild(mainPanel)
        }

        override fun render(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
            root.draw(matrixStack, mouseX, mouseY, delta)
            super.render(matrixStack, mouseX, mouseY, delta)
        }
    }
}
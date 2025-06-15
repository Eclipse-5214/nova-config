package co.eclipse5214.novaconfig.ui

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.universal.UMatrixStack
import gg.essential.elementa.dsl.*
import net.minecraft.client.gl.ShaderProgram
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import java.awt.Color

class ConfigUIScreen : Screen(Text.of("Config UI")) {
    private val root = Window(ElementaVersion.V2)
    private val background = UIRoundedRectangle(10f)
    private val card = UIRoundedRectangle(10f)

    val shader: ShaderEff

    init {
        background
            .setWidth(PixelConstraint(600f))
            .setHeight(PixelConstraint(350f))
            .setX(CenterConstraint())
            .setY(CenterConstraint())
            .setColor(NovaPalette.Base)
            .setChildOf(root)
        card
            .setWidth(PixelConstraint(450f))
            .setHeight(PixelConstraint(300f))
            .setX(CenterConstraint() + PixelConstraint(50f))
            .setY(CenterConstraint())
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        root.draw(UMatrixStack()) // Render UI
    }
}
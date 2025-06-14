package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.Config
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.universal.UMatrixStack
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.ScissorEffect
import net.minecraft.client.gl.ShaderProgram
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.awt.Color

class ConfigUIScreen (private val config: Config) : Screen(Text.of(config.name)) {
    private val root = Window(ElementaVersion.V2)
    private val bgOutline = UIRoundedRectangle(10f)
    private val background = UIRoundedRectangle(10f)
    private val cardOutline = UIRoundedRectangle(10f)
    private val card = UIRoundedRectangle(10f)
    private val title = UIText(config.name)

    init {
        bgOutline
            .setWidth(PixelConstraint(602f))
            .setHeight(PixelConstraint(352f))
            .setX(CenterConstraint())
            .setY(CenterConstraint())
            .setColor(NovaPalette.Sapphire)
            .setChildOf(root)
        background
            .setWidth(PixelConstraint(600f))
            .setHeight(PixelConstraint(350f))
            .setX(CenterConstraint())
            .setY(CenterConstraint())
            .setColor(NovaPalette.Base)
            .setChildOf(root)
        cardOutline
            .setWidth(PixelConstraint(477f))
            .setHeight(PixelConstraint(352f))
            .setX(CenterConstraint() + PixelConstraint(63f))
            .setY(CenterConstraint())
            .setColor(NovaPalette.Mauve)
            .setChildOf(root)
        card
            .setWidth(PixelConstraint(475f))
            .setHeight(PixelConstraint(350f))
            .setX(CenterConstraint() + PixelConstraint(63f))
            .setY(CenterConstraint())
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

        title
            .setX(CenterConstraint() - PixelConstraint(240f))
            .setY(CenterConstraint() - PixelConstraint(150f))
            .setChildOf(root)
            .setTextScale((1.5f).pixels())
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        root.draw(UMatrixStack()) // Render UI
    }
}
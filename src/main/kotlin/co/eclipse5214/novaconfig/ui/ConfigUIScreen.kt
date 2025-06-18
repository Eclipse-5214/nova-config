package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.ui.NovaPalette.withAlpha
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
    private val list = UIBlock()
    private val card = UIBlock()
    private val title = UIText(config.name)

    init {
        //background card

        list
            .setWidth(PixelConstraint(600f))
            .setHeight(PixelConstraint(350f))
            .setX(CenterConstraint())
            .setY(CenterConstraint())
            .setColor(NovaPalette.Base.withAlpha(150))
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

        // Buttons

        config.categories.forEachIndexed { index, category ->
            val button = UIRoundedRectangle(10f) // Rounded corners
                .setWidth(PixelConstraint(180f))
                .setHeight(PixelConstraint(40f))
                .setX(PixelConstraint(20f)) // Sidebar positioning
                .setY(PixelConstraint(index * 50f + 20f)) // Stack buttons vertically
                .setColor(NovaPalette.Surface1)
                .setChildOf(root)

            val label = UIText(category.name)
                .setX(CenterConstraint())
                .setY(CenterConstraint())
                .setChildOf(button)

            //button.onClick {
                //loadCategory(category) // Function to update UI with selected category
            //}
        }
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        root.draw(UMatrixStack()) // Render UI
    }
}
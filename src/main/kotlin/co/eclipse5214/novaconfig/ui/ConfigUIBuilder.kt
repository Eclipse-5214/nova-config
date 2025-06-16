package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.model.elements.Toggle
import co.eclipse5214.novaconfig.model.elements.TextParagraph
import co.eclipse5214.novaconfig.model.elements.Subcategory
import co.eclipse5214.novaconfig.utils.TickScheduler
import co.eclipse5214.novaconfig.utils.chatutils
import gg.essential.universal.UScreen
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import java.awt.Color

class ConfigUIBuilder(private val config: Config) {
    fun build(): ConfigUI {
        return object : ConfigUI {
            override val screen = ConfigUIScreen(config) // Attach a proper WindowScreen instance

            override fun show() {
                chatutils.clientMsg("Showing config screen", false)

                TickScheduler.schedule(1) {
                    MinecraftClient.getInstance().setScreen(screen)
                    chatutils.clientMsg("Screen Should be showing", false)
                }
            }

            override fun hide() {

            }
        }
    }
}
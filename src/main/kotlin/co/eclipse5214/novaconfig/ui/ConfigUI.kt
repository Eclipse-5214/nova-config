package co.eclipse5214.novaconfig.ui

import net.minecraft.client.gui.screen.Screen

interface ConfigUI {
    val screen: Screen

    fun show()
    fun hide()
}
package co.eclipse5214.novaconfig.ui.elements

import co.eclipse5214.novaconfig.model.elements.Keybind
import co.eclipse5214.novaconfig.ui.NovaPalette
import co.eclipse5214.novaconfig.utils.chatutils
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*
import org.lwjgl.glfw.GLFW
import java.awt.Color

class KeybindUIBuilder {
    var listening = false

    fun build(root: UIComponent, keybind: Keybind): UIComponent {
        val container = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 60.pixels()
                x = CenterConstraint()
                y = PixelConstraint(20f)
            }
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

        val name = UIText(keybind.name)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(5f)
            }
            .setChildOf(container)

        val desc = UIText("§7" + keybind.description)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(17f)
            }
            .setChildOf(container)

        val displayContainer = UIRoundedRectangle(5f)
            .constrain {
                width = 50.pixels()
                height = 20.pixels()
                x = PixelConstraint(350f)  // Positions it on the right
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface2)
            .setChildOf(container)

        val keyDisplay = UIText(getKeyName(keybind.value as Int))
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setColor(Color.WHITE)
            .setChildOf(displayContainer)

        displayContainer.onMouseClick {
            grabWindowFocus()
            listening = true
            (keyDisplay as UIText)
                .setText("...")
                .setColor(NovaPalette.Mauve)
        }

        displayContainer.onKeyType { key, keycode ->
            if (listening) {
                (keyDisplay as UIText)
                    .setText(getKeyName(keycode))
                    .setColor(Color.WHITE)

                keybind.value = keycode
                listening = false
            }
        }

        return container
    }

    private fun getKeyName(keyCode: Int): String = when (keyCode) {
        340 -> "LShift"
        344 -> "RShift"
        341 -> "LCtrl"
        345 -> "RCtrl"
        342 -> "LAlt"
        346 -> "RAlt"
        257 -> "Enter"
        256 -> "Escape"
        in 290..301 -> "F${keyCode - 289}" // F1–F12
        else -> GLFW.glfwGetKeyName(keyCode, 0) ?: "Key$keyCode"
    }
}

package co.stellarskys.novaconfig.ui.elements

import co.stellarskys.novaconfig.core.TextInput
import co.stellarskys.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.components.input.UITextInput
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*

class TextInputUIBuilder {
    fun build(root: UIComponent, input: TextInput): UIComponent {
        val container = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 60.pixels()
                x = CenterConstraint()
                y = PixelConstraint(20f)
            }
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

        val name = UIText(input.name)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(5f)
            }
            .setChildOf(container)

        val desc = UIWrappedText("§7" + input.description)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(17f)
                width = 230.pixels()
            }
            .setChildOf(container)

        val inputContainer = UIRoundedRectangle(5f)
            .constrain {
                width = 100.pixels()
                height = 18.pixels()
                x = PixelConstraint(310f)
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface2)
            .setChildOf(container)


        val inputText = UITextInput(input.value as String, true)
            .constrain {
                width = 94.pixels()
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setChildOf(inputContainer)

        inputContainer.onMouseClick {
            inputText.grabWindowFocus()
        }

        inputText.onKeyType { _, _ ->
            input.onValueChanged?.invoke((inputText as UITextInput).getText())
        }

        inputText.onFocusLost {
            val itext = inputText as UITextInput
            input.value = itext.getText()
            itext.setText(input.value as String)
        }

        return container
    }
}

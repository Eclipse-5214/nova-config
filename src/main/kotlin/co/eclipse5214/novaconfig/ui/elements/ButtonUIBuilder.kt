package co.eclipse5214.novaconfig.ui.elements

import co.eclipse5214.novaconfig.model.elements.Button
import co.eclipse5214.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*


class ButtonUIBuilder {
    fun build(root: UIComponent, button: Button): UIComponent {
        val container = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 60.pixels()
                x = CenterConstraint()
                y = PixelConstraint(20f)
            }
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

        val name = UIText(button.name)
            .constrain {
                x = PixelConstraint(7f) // Moves text to the left
                y = PixelConstraint(5f)
            }
            .setChildOf(container)

        val desc = UIText("§7" + button.description)
            .constrain {
                x = PixelConstraint(7f) // Ensures description is left-aligned too
                y = PixelConstraint(17f)
            }
            .setChildOf(container)

        val buttonInput = UIRoundedRectangle(5f)
            .constrain {
                width = 50.pixels()
                height = 20.pixels()
                x = PixelConstraint(350f)  // Positions it on the right
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface2)
            .setChildOf(container)

        val buttonText = UIText(button.placeholder)
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setChildOf(buttonInput)

        buttonInput.onMouseClick {
            button.onClick?.invoke()

            animate {
                setColorAnimation(
                    Animations.OUT_CUBIC,
                    0.1f,
                    NovaPalette.Mauve.toConstraint()
                )

                onComplete {
                    animate {
                        setColorAnimation(
                            Animations.IN_OUT_QUINT,
                            0.2f,
                            NovaPalette.Surface2.toConstraint()
                        )
                    }
                }
            }
        }

        return  container
    }
}
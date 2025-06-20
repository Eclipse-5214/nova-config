package co.eclipse5214.novaconfig.ui.elements

import co.eclipse5214.novaconfig.model.elements.Toggle
import co.eclipse5214.novaconfig.ui.NovaPalette
import co.eclipse5214.novaconfig.utils.TickScheduler
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*

class ToggleUIBuilder {
    fun build(root: UIComponent, toggle: Toggle, refresh: () -> Unit): UIComponent {
        val toggleContainer = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 60.pixels()
                x = CenterConstraint()
                y = PixelConstraint(20f)
            }
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

        val toggleName = UIText(toggle.name)
            .constrain {
                x = PixelConstraint(7f) // Moves text to the left
                y = PixelConstraint(5f)
            }
            .setChildOf(toggleContainer)

        val toggleDesc = UIText("§7" + toggle.description)
            .constrain {
                x = PixelConstraint(7f) // Ensures description is left-aligned too
                y = PixelConstraint(17f)
            }
            .setChildOf(toggleContainer)

        val toggleSwitch = UIRoundedRectangle(5f)
            .constrain {
                width = 50.pixels()
                height = 20.pixels()
                x = PixelConstraint(350f)  // Positions it on the right
                y = CenterConstraint()
            }
            .setColor(if (toggle.value as Boolean) NovaPalette.Mauve else NovaPalette.Surface2)
            .setChildOf(toggleContainer)

        val toggleKnob = UIRoundedRectangle(5f)
            .constrain {
                width = 18.pixels()
                height = 18.pixels()
                x = PixelConstraint(if (toggle.value as Boolean) 30f else 2f) // Moves knob left/right
                y = CenterConstraint()
            }
            .setColor(if (toggle.value as Boolean) NovaPalette.Text else NovaPalette.Surface1)
            .setChildOf(toggleSwitch)

        toggleSwitch.onMouseClick {
            toggle.value = !(toggle.value as Boolean) // Flip toggle state

            toggleKnob.animate {
                setXAnimation(
                    Animations.OUT_CUBIC,
                    0.2f,
                    PixelConstraint(if (toggle.value as Boolean) 30f else 2f) // Moves knob accordingly
                )

                setColorAnimation(
                    Animations.OUT_CUBIC,
                    0.2f,
                    (if (toggle.value as Boolean) NovaPalette.Text else NovaPalette.Surface1).toConstraint()
                )
            }

            this.animate {
                setColorAnimation(
                    Animations.OUT_CUBIC,
                    0.2f,
                    (if (toggle.value as Boolean) NovaPalette.Mauve else NovaPalette.Surface2).toConstraint()
                )
            }

            println("[Toggle] ${toggle.id} set to ${toggle.value}")

            TickScheduler.schedule(4){
                refresh()
            }
        }

        return toggleContainer
    }
}

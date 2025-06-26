package co.stellarskys.novaconfig.ui.elements

import co.stellarskys.novaconfig.model.elements.Toggle
import co.stellarskys.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*

/**
 * Responsible for constructing the visual toggle component bound to a config boolean value.
 *
 * Includes a label, description text, and a switch with an animated knob to reflect the current state.
 * The toggle updates its internal value on click and triggers a refresh lambda to recompute visibility logic.
 */

class ToggleUIBuilder {

    /**
     * Builds a toggle UI component bound to the given Toggle element.
     *
     * The toggle includes a label, description, and a switch with an animated knob.
     * Clicking the switch updates the Toggle's value and invokes the provided refresh callback.
     *
     * @param root The UIComponent to attach the toggle container to.
     * @param toggle The Toggle data model representing the option.
     * @param refresh A lambda that will be invoked after the toggle is clicked, typically triggering a partial UI redraw.
     * @return A fully constructed UIComponent representing the toggle.
     */

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

            refresh()
        }

        return toggleContainer
    }
}

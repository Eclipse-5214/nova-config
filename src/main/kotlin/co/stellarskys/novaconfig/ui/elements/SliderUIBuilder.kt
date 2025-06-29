package co.stellarskys.novaconfig.ui.elements

import co.stellarskys.novaconfig.model.elements.Slider
import co.stellarskys.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.components.input.UITextInput
import gg.essential.elementa.constraints.*
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import java.text.DecimalFormat

class SliderUIBuilder {
    fun build(root: UIComponent, slider: Slider): UIComponent {
        val container = UIRoundedRectangle(6f) // use UIRoundedRectangle(6f) when it's working again
            .constrain {
                width = 425.pixels()
                height = 60.pixels()
                x = CenterConstraint()
                y = PixelConstraint(20f)
            }
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

        val name = UIText(slider.name)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(5f)
            }
            .setChildOf(container)

        val desc = UIWrappedText("§7" + slider.description)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(17f)
                width = 230.pixels()
            }
            .setChildOf(container)

        val track = UIRoundedRectangle(5f)
            .constrain {
                width = 100.pixels()
                height = 5.pixels()
                x = PixelConstraint(270f)
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface2)
            .setChildOf(container)

        val knob = UIRoundedRectangle(5f) // swap to UIRoundedRectangle(5f) later
            .constrain {
                width = 10.pixels()
                height = 10.pixels()
                x = PixelConstraint(270f)
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Mauve)
            .setChildOf(container)

        val inputContainer = UIRoundedRectangle(5f)
            .constrain {
                width = 20.pixels()
                height = 20.pixels()
                x = PixelConstraint(380f)  // Positions it on the right
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface2)
            .setChildOf(container)

        val valueInput = UITextInput("", true) // we'll update this as value changes
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint()
                width = 16.pixels()
            }
            .setChildOf(inputContainer)

        // Drag behavior
        val sliderMin = slider.min
        val sliderMax = slider.max
        val sliderWidth = 100f

        fun updateSliderPosition(mouseX: Float) {
            val clamped = mouseX.coerceIn(0f, sliderWidth)
            val percent = clamped / sliderWidth
            val value = sliderMin + (sliderMax - sliderMin) * percent
            slider.value = value
            knob.animate {
                setXAnimation(
                    Animations.OUT_CUBIC,
                    0.2f,
                    PixelConstraint(270f + clamped - 5f) // Moves knob accordingly
                )
            }
            (valueInput as UITextInput).setText(DecimalFormat("#.#").format(value))
        }

        track.onMouseClick { event -> updateSliderPosition(event.relativeX) }

        track.onMouseDrag { x , y , _ ->
            val withinY = y in -5f..(track.getHeight().toFloat() + 5f)
            if (!withinY) return@onMouseDrag

            updateSliderPosition(x)
        }

        inputContainer.onMouseClick {
            valueInput.grabWindowFocus()
        }

        valueInput.onFocusLost {
            valueInput as UITextInput
            val text = valueInput.getText()
            val parsed = text.toFloatOrNull()

            if (parsed != null) {
                val clamped = parsed.coerceIn(sliderMin, sliderMax)
                updateSliderPosition(((clamped - sliderMin) / (sliderMax - sliderMin)) * sliderWidth)
                valueInput.setText(DecimalFormat("#.#").format(clamped))
            } else {
                // Reset to current value if input is invalid
                val fallback = DecimalFormat("#.#").format(slider.value)
                valueInput.setText(fallback)
            }
        }

        // Set initial value
        updateSliderPosition((((slider.value as Float) - sliderMin) / (sliderMax - sliderMin)) * sliderWidth)

        return container
    }
}
package co.stellarskys.novaconfig.ui.elements

import co.stellarskys.novaconfig.model.elements.StepSlider
import co.stellarskys.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.components.input.UITextInput
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*

class StepSliderUIBuilder {
    fun build(root: UIComponent, slider: StepSlider): UIComponent {
        val container = UIRoundedRectangle(6f)
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

        val desc = UIText("§7" + slider.description)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(17f)
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

        val knob = UIRoundedRectangle(5f)
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
                x = PixelConstraint(380f)
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface2)
            .setChildOf(container)

        val valueInput = UITextInput("", true)
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint()
                width = 16.pixels()
            }
            .setChildOf(inputContainer)

        // Behavior
        val min = slider.min
        val max = slider.max
        val step = slider.step
        val range = max - min
        val sliderWidth = 100f

        val numSteps = range / step
        fun quantize(value: Int): Int =
            ((value - min + step / 2) / step) * step + min

        fun updateSliderPositionFromStep(value: Int) {
            val clamped = value.coerceIn(min, max)
            val percent = (clamped - min).toFloat() / range.toFloat()
            val knobX = 270f + percent * sliderWidth - 5f

            slider.value = clamped
            knob.setX(PixelConstraint(knobX))
            (valueInput as UITextInput).setText(clamped.toString())
        }

        fun updateSliderPositionFromMouse(mouseX: Float) {
            val clamped = mouseX.coerceIn(0f, sliderWidth)
            val percent = clamped / sliderWidth
            val rawValue = (min + (range * percent)).toInt()
            updateSliderPositionFromStep(quantize(rawValue))
        }

        track.onMouseClick { event -> updateSliderPositionFromMouse(event.relativeX) }
        track.onMouseDrag { x, y, _ ->
            val withinY = y in -5f..(track.getHeight().toFloat() + 5f)
            if (!withinY) return@onMouseDrag

            updateSliderPositionFromMouse(x)
        }

        inputContainer.onMouseClick {
            valueInput.grabWindowFocus()
        }

        valueInput.onFocusLost {
            valueInput as UITextInput
            val text = valueInput.getText()
            val parsed = text.toIntOrNull()
            if (parsed != null) {
                updateSliderPositionFromStep(quantize(parsed))
            } else {
                valueInput.setText(slider.value.toString())
            }
        }

        // Set initial
        updateSliderPositionFromStep(slider.value as Int)

        return container
    }
}

package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.builder.ElementBuilder
import co.eclipse5214.novaconfig.model.elements.Slider

class SliderBuilder : ElementBuilder() {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String
    var min: Float = 0f
    var max: Float = 100f
    var default: Float = 0f

    fun build(): Slider {
        return Slider(
            configName = configName,
            name = name,
            description = description,
            min = min,
            max = max,
            default = default
        ).also { it.shouldShow = shouldShow }
    }
}
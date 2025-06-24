package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.builder.ElementBuilder
import co.eclipse5214.novaconfig.model.elements.StepSlider

class StepSliderBuilder : ElementBuilder() {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String
    var min: Int = 0
    var max: Int = 10
    var default: Int = 0
    var step: Int = 1

    fun build(): StepSlider {
        require(step >= 1) { "Step must be at least 1" }
        return StepSlider(
            configName = configName,
            name = name,
            description = description,
            min = min,
            max = max,
            default = default,
            step = step
        ).also { it.shouldShow = shouldShow }
    }
}

package co.eclipse5214.novaconfig.builder.elements

import co.eclipse5214.novaconfig.builder.ElementBuilder
import co.eclipse5214.novaconfig.model.elements.ColorPicker
import co.eclipse5214.novaconfig.model.elements.RGBA

class ColorPickerBuilder: ElementBuilder() {
    lateinit var configName: String
    lateinit var name: String
    lateinit var description: String
    var default:  RGBA = RGBA(255,255,255,255)

    fun rgba(r: Int, g: Int, b: Int, a: Int = 255): RGBA = RGBA(r, g, b, a)

    fun build(): ColorPicker {
        return ColorPicker(
            configName = configName,
            name = name,
            description = description,
            default = default,
        ).also { it.shouldShow = shouldShow }
    }
}
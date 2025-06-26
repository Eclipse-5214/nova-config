package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.RGBA
import co.stellarskys.novaconfig.model.ConfigElement

data class ColorPicker(
    val configName: String,
    val name: String,
    val description: String,
    val default: RGBA = RGBA(255, 255, 255, 255)
) : ConfigElement(
    id = configName,
    value = default
)
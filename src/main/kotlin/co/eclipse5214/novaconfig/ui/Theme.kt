package co.eclipse5214.novaconfig.ui

import java.awt.Color

data class Theme(
    val colors: Map<String, Color>
) {
    operator fun get(key: String): Color? = colors[key]
}
package co.stellarskys.novaconfig.ui

import java.awt.Color

data class Theme(
    val colors: Map<String, Color>
) {
    operator fun get(key: String): Color? = colors[key]
}
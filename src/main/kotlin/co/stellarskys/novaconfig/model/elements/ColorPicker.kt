package co.stellarskys.novaconfig.model.elements

import co.stellarskys.novaconfig.model.ConfigElement
import java.awt.Color

data class RGBA(val r: Int, val g: Int, val b: Int, val a: Int = 255) {
    fun toColor(includeAlpha: Boolean = true): Color {
        return if (includeAlpha) {
            Color(r, g, b, a)
        } else {
            Color(r, g, b)
        }
    }

    fun toHex(includeAlpha: Boolean = true): String {
        return if (includeAlpha) {
            String.format("#%02x%02x%02x%02x", r, g, b, a)
        } else {
            String.format("#%02x%02x%02x", r, g, b)
        }
    }

    fun toHSVA(): FloatArray {
        val hsv = Color.RGBtoHSB(r, g, b, null)
        return floatArrayOf(hsv[0], hsv[1], hsv[2], a / 255f)
    }

    companion object {
        fun fromHex(hex: String): RGBA {
            val cleaned = hex.trim().lowercase().removePrefix("#")

            val expanded = when (cleaned.length) {
                3 -> cleaned.map { "$it$it" }.joinToString("") + "ff"        // rgb → rrggbb + alpha = ff
                4 -> cleaned.map { "$it$it" }.joinToString("")               // rgba → rrggbbaa
                6, 8 -> cleaned
                else -> throw IllegalArgumentException("Invalid hex color: $hex")
            }

            val r = expanded.substring(0, 2).toInt(16)
            val g = expanded.substring(2, 4).toInt(16)
            val b = expanded.substring(4, 6).toInt(16)
            val a = if (expanded.length == 8) expanded.substring(6, 8).toInt(16) else 255

            return RGBA(r, g, b, a)
        }

        fun fromHSVA(hue: Float, saturation: Float, value: Float, alpha: Float = 1f): RGBA {
            val rgb = Color.getHSBColor(hue, saturation, value)
            val a = (alpha.coerceIn(0f, 1f) * 255).toInt()
            return RGBA(rgb.red, rgb.green, rgb.blue, a)
        }
    }

}

data class ColorPicker(
    val configName: String,
    val name: String,
    val description: String,
    val default: RGBA = RGBA(255, 255, 255, 255)
) : ConfigElement(
    id = configName,
    value = default
)
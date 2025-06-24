package co.eclipse5214.novaconfig.ui

import kotlinx.serialization.json.*
import java.awt.Color
import java.io.File

/**
 * Loads a theme from a JSON file on disk.
 *
 * The JSON must be a flat object with string keys and hex color values, e.g.:
 * {
 *   "Surface0": "#414559",
 *   "Mauve": "#ca9ee6"
 * }
 */
object ThemeLoader {
    /**
     * Attempts to parse a theme from the given file.
     * Returns null if the file is missing, unreadable, or malformed.
     *
     * @param file The theme JSON file to load, or null to skip.
     * @return A [Theme] instance or null on failure.
     */
    fun loadThemeOrNull(file: File?): Theme? {
        if (file == null || !file.exists()) return null

        return try {
            val raw = file.readText()
            val parsed = Json.parseToJsonElement(raw).jsonObject

            val colors = parsed.mapNotNull { (name, json) ->
                val value = json.jsonPrimitive.content
                try {
                    name to Color.decode(value)
                } catch (e: IllegalArgumentException) {
                    println("Skipping invalid color for key '$name': '$value'")
                    null
                }
            }.toMap()

            Theme(colors)
        } catch (e: Exception) {
            println("Failed to load theme: ${e.message}")
            null
        }
    }
}
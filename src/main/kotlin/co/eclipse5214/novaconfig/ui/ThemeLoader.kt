package co.eclipse5214.novaconfig.ui

import java.io.File
import java.awt.Color
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object ThemeLoader {
    fun loadThemeOrNull(file: File?): Theme? {
        if (file == null || !file.exists()) return null

        return try {
            val raw = file.readText()
            val parsed = Json.parseToJsonElement(raw).jsonObject

            val colors = parsed.mapValues { (_, json) ->
                Color.decode(json.jsonPrimitive.content)
            }

            Theme(colors)
        } catch (e: Exception) {
            println("Failed to load theme: ${e.message}")
            null
        }
    }
}

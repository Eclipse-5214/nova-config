package co.eclipse5214.novaconfig.core

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.ui.ConfigUI
import co.eclipse5214.novaconfig.ui.ConfigUIBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import java.io.File

class ConfigInstance(
    val config: Config,
    val modId: String,
    private val file: File? = null,
    private val uiFactory: (Config) -> ConfigUI = { ConfigUIBuilder(it).build() }
) {
    private val resolvedFile: File
        get() = file ?: File("config/$modId/settings.json")

    private var ui: ConfigUI? = null

    private fun buildIfNeeded() {
        if (ui == null) ui = uiFactory(config)
    }

    fun open() {
        buildIfNeeded()
        ui?.show()
    }

    fun close() {
        ui?.hide()
    }

    fun resetUI() {
        ui = null
    }

    fun save() {
        try {
            val target = resolvedFile
            target.parentFile?.mkdirs()

            val json = config.toJson()
            println(json)

            val jsonOutput = Json {
                prettyPrint = true
            }

            val jsonString = jsonOutput.encodeToString(JsonObject.serializer(), json)
            target.writeText(jsonString)

        } catch (e: Exception) {
            println("Failed to save config for '$modId': ${e.message}")
            e.printStackTrace()
        }
    }


    operator fun get(key: String): Any? =
        config.categories
            .flatMap { it.elements }
            .find { it.id == key }
            ?.value
}
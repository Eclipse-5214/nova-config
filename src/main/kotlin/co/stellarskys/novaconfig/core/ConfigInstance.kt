package co.stellarskys.novaconfig.core

import co.stellarskys.novaconfig.model.Config
import co.stellarskys.novaconfig.ui.ConfigUI
import co.stellarskys.novaconfig.ui.ConfigUIBuilder
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

    private var loaded = false

    fun ensureLoaded() {
        if (!loaded) {
            load()
            loaded = true
        }
    }

    private fun buildIfNeeded() {
        if (ui == null) ui = uiFactory(config)
        ensureLoaded()
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

    fun load() {
        println("[Nova] load ran")
        try {
            val target = resolvedFile
            if (!target.exists()) return

            val jsonText = target.readText()
            val loadedJson = Json.decodeFromString(JsonObject.serializer(), jsonText)

            // Inject into config
            config.fromJson(loadedJson)

        } catch (e: Exception) {
            println("Failed to load config for '$modId': ${e.message}")
            e.printStackTrace()
        }
    }

    inline fun <reified T> getConfigValue(ID: String): T? {
        ensureLoaded()
        return config.categories
            .flatMap { it.elements }
            .find { it.id == ID }
            ?.value as T
    }

    operator fun get(key: String): Any? {
        ensureLoaded()
        return config.categories
            .flatMap { it.elements }
            .find { it.id == key }
            ?.value
    }
}
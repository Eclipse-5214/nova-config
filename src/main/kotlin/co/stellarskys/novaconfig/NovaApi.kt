package co.stellarskys.novaconfig

import co.stellarskys.novaconfig.core.ConfigInstance
import co.stellarskys.novaconfig.builder.ConfigBuilder
import java.io.File

/**
 * The entry point for defining and accessing NovaConfig-based things.
 *
 * This object manages all registered configs across different mods, handles instantiation,
 * and optionally binds a file location for serialization. Mods can use this DSL to build rich,
 * interactive config screens that also look beautiful (in my humble opinion).
 *
 * Example usage:
 * ```
 * val config = NovaApi.createConfig("example", "mymod") {
 *     category("General") {
 *         toggle {
 *             configName = "enable_feature"
 *             name = "Enable Feature"
 *             description = "Turns the feature on or off"
 *             default = true
 *         }
 *     }
 * }
 * ```
 */
object NovaApi {

    /** Internal storage for all created configs, indexed by name. */
    private val allConfigs = mutableMapOf<String, ConfigInstance>()

    /**
     * Creates a new config definition and registers it under the given [name].
     *
     * @param name The unique name/key of this config (e.g., `"example"`).
     * @param modId The mod ID this config belongs to (used for saving purposes).
     * @param file Optional file destination for saving and loading the config.
     * @param builder A DSL block to populate the config structure.
     * @return A [ConfigInstance] representing the built config, ready to be shown or saved.
     */
    fun createConfig(
        name: String,
        modId: String,
        file: File? = null,
        builder: ConfigBuilder.() -> Unit
    ): ConfigInstance {
        val configBuilder = ConfigBuilder(name).apply(builder)
        val config = configBuilder.build()
        val instance = ConfigInstance(config, modId, file)
        allConfigs[name] = instance
        return instance
    }

    /**
     * Saves all registered configs to disk.
     * Typically called internally or during shutdown.
     */
    internal fun saveAllConfigs() {
        allConfigs.values.forEach { it.save() }
    }

    /**
     * Provides quick access to a registered config instance by name.
     *
     * @param name The name used when the config was created.
     * @return The [ConfigInstance], or `null` if none has been registered.
     */
    operator fun get(name: String): ConfigInstance? = allConfigs[name]
}

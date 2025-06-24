package co.eclipse5214.novaconfig

import co.eclipse5214.novaconfig.core.ConfigInstance
import co.eclipse5214.novaconfig.builder.ConfigBuilder
import java.io.File

object NovaApi {
    private val allConfigs = mutableMapOf<String, ConfigInstance>()

    fun createConfig(name: String, modId: String, file: File? = null, builder: ConfigBuilder.() -> Unit): ConfigInstance {
        val configBuilder = ConfigBuilder(name).apply(builder)
        val config = configBuilder.build()
        val instance = ConfigInstance(config, modId, file) // Updated to pass file
        allConfigs[name] = instance
        return instance
    }

    internal fun saveAllConfigs() {
        allConfigs.values.forEach { it.save() }
    }

    operator fun get(name: String): ConfigInstance? = allConfigs[name]
}
package co.eclipse5214.novaconfig

import co.eclipse5214.novaconfig.core.ConfigInstance
import co.eclipse5214.novaconfig.ui.ConfigUI
import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.builder.ConfigBuilder
import co.eclipse5214.novaconfig.ui.ConfigUIBuilder

object NovaApi {
    private val allConfigs = mutableMapOf<String, ConfigInstance>()

    fun createConfig(name: String, builder: ConfigBuilder.() -> Unit): ConfigInstance {
        val configBuilder = ConfigBuilder(name).apply(builder)
        val config = configBuilder.build()
        val instance = ConfigInstance(config) // Removed uiFactory
        allConfigs[name] = instance
        return instance
    }

    operator fun get(name: String): ConfigInstance? = allConfigs[name]
}
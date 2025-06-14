package co.eclipse5214.novaconfig

import co.eclipse5214.novaconfig.core.ConfigInstance
import co.eclipse5214.novaconfig.ui.ConfigUI
import co.eclipse5214.novaconfig.model.Config

object NovaApi {
    private val allConfigs = mutableMapOf<String, Config>()

    private val allConfigs = mutableMapOf<String, ConfigInstance>()

    fun createConfig(
        name: String,
        builder: ConfigBuilder.() -> Unit,
        uiFactory: (Config) -> ConfigUI = { ConfigUIBuilder(it).build() }
    ): ConfigInstance {
        val configBuilder = ConfigBuilder(name).apply(builder)
        val config = configBuilder.build()
        val instance = ConfigInstance(config, uiFactory)
        allConfigs[name] = instance
        return instance
    }

    operator fun get(name: String): ConfigInstance? = allConfigs[name]

    operator fun get(name: String): Config? = allConfigs[name]
}
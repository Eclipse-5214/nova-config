package co.eclipse5214.novaconfig.core

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.ui.ConfigUI
import co.eclipse5214.novaconfig.ui.ConfigUIBuilder

class ConfigInstance(
    val config: Config,
    private val uiFactory: (Config) -> ConfigUI = { ConfigUIBuilder(it).build() }
) {
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

    operator fun get(key: String): Any? =
        config.categories
            .flatMap { it.elements }
            .find { it.id == key }
            ?.value
}
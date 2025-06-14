package co.eclipse5214.novaconfig.builder

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.model.ConfigCategory

class ConfigBuilder(val name: String) {
    val categories = mutableListOf<ConfigCategory>()

    fun category(name: String, builder: CategoryBuilder.() -> Unit) {
        val builtCategory = CategoryBuilder(name).apply(builder).build()
        categories += builtCategory
    }

    fun build(): Config {
        return Config(name, categories)
    }
}

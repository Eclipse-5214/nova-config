package co.eclipse5214.novaconfig.builder

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.ui.ThemeLoader
import co.eclipse5214.novaconfig.ui.NovaPalette
import java.io.File

class ConfigBuilder(val name: String) {
    var themeFile: File? = null
    val categories = mutableListOf<ConfigCategory>()

    fun setTheme(file: File) {
        themeFile = file
        ThemeLoader.loadThemeOrNull(file)?.let { NovaPalette.init(it) }
    }

    fun category(name: String, builder: CategoryBuilder.() -> Unit) {
        val builtCategory = CategoryBuilder(name).apply(builder).build()
        categories += builtCategory
    }

    fun markdowncatagory(name: String, path: String){
        val buildCategory = MarkdownCatagoryBuilder(name, path).build()
        categories += buildCategory
    }

    fun build(): Config {
        return Config(name, categories)
    }
}

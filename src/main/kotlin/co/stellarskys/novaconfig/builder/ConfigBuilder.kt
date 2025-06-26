package co.stellarskys.novaconfig.builder

import co.stellarskys.novaconfig.model.Config
import co.stellarskys.novaconfig.model.ConfigCategory
import co.stellarskys.novaconfig.ui.ThemeLoader
import co.stellarskys.novaconfig.ui.NovaPalette
import java.io.File

/**
 * A builder for constructing a Nova configuration definition.
 *
 * This class allows registering themed UI styles, categories,
 * and markdown sections in a modular and declarative way.
 *
 * @property name The name of the configuration file (e.g., "example").
 */
class ConfigBuilder(val name: String) {

    /**
     * An optional file representing the theme to be applied to this config UI.
     * When set using [setTheme], it will be loaded and applied via [ThemeLoader].
     */
    var themeFile: File? = null

    /**
     * The internal list of categories that make up this config.
     * These include both standard categories and markdown-based sections.
     */
    val categories = mutableListOf<ConfigCategory>()

    /**
     * Sets a visual theme file and applies it immediately.
     *
     * @param file The theme file to load (typically JSON).
     */
    fun setTheme(file: File) {
        themeFile = file
        ThemeLoader.loadThemeOrNull(file)?.let { NovaPalette.init(it) }
    }

    /**
     * Adds a new standard config category with interactive elements.
     *
     * @param name The display name of the category tab.
     * @param builder The DSL block to populate this category.
     */
    fun category(name: String, builder: CategoryBuilder.() -> Unit) {
        val builtCategory = CategoryBuilder(name).apply(builder).build()
        categories += builtCategory
    }

    /**
     * Adds a read-only markdown-based category section.
     * This can be used for in-game docs, setup guides, changelogs, etc.
     *
     * @param name The display name of the markdown category tab.
     * @param markdown The parsed content of the markdown file (preloaded as a string).
     */
    fun markdowncatagory(name: String, markdown: String) {
        val buildCategory = MarkdownCatagoryBuilder(name, markdown).build()
        categories += buildCategory
    }

    /**
     * Builds and returns a complete [Config] instance containing
     * all registered categories and theme info.
     *
     * @return The constructed [Config] object.
     */
    fun build(): Config {
        return Config(name, categories)
    }
}
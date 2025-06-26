package co.stellarskys.novaconfig.builder

import co.stellarskys.novaconfig.model.ConfigCategory
import co.stellarskys.novaconfig.model.ConfigElement

/**
 * A builder class for creating a read-only markdown-based config category.
 *
 * Markdown categories are rendered as scrollable, stylized documents inside the config UI.
 * These are perfect for tutorials, changelogs, descriptions, or even hidden easter eggs.
 *
 * @param name The display name of the category tab.
 * @param markdown The pre-rendered markdown content to display.
 */
class MarkdownCatagoryBuilder(private val name: String, private val markdown: String) {

    /**
     * Internally stores a placeholder list of elements for structural compatibility.
     * Markdown categories do not contain interactive config elements.
     */
    val elements = mutableListOf<ConfigElement>()

    /**
     * Builds a [ConfigCategory] from the provided markdown and name.
     * Marks the category as markdown-based for special rendering in the UI.
     *
     * @return A [ConfigCategory] that displays the provided markdown content.
     */
    fun build(): ConfigCategory {
        elements += ConfigElement(id = null) // placeholder for layout consistency
        return ConfigCategory(name, elements, isMarkdown = true, markdown = markdown)
    }
}

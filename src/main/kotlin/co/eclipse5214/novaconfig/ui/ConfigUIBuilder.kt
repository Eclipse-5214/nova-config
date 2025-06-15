package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.model.elements.Toggle
import co.eclipse5214.novaconfig.model.elements.TextParagraph
import co.eclipse5214.novaconfig.model.elements.Subcategory


class ConfigUIBuilder(private val config: Config) {

    fun build(): ConfigUI {
        return object : ConfigUI {
            override fun show() {
                println("Opening config: ${config.name}")
                for (category in config.categories) {
                    println("Category: ${category.name}")
                    for (element in category.elements) {
                        when (element) {
                            is Toggle -> println("  [Toggle] ${element.name}: ${element.description} (default: ${element.default})")
                            is TextParagraph -> println("  [Text] ${element.description}")
                            is Subcategory -> println("  [Subcategory] ${element.name}")
                            else -> println("  [Unknown Element]")
                        }
                    }
                }
            }

            override fun hide() {
                println("Closing config: ${config.name}")
            }
        }
    }
}
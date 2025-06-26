package co.stellarskys.novaconfig.ui

import co.stellarskys.novaconfig.model.Config
import co.stellarskys.novaconfig.model.ConfigCategory
import co.stellarskys.novaconfig.utils.TickScheduler
import co.stellarskys.novaconfig.ui.NovaPalette.withAlpha
import net.minecraft.client.MinecraftClient
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.UIComponent
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*
import java.awt.Color

/**
 * Responsible for constructing the main configuration UI screen for NovaConfig.
 *
 * This screen includes:
 * - A left-side category selector panel
 * - A scrollable config panel on the right
 * - Dynamic switching between category views
 *
 * @param config The full configuration object containing category metadata and element values.
 */
class ConfigUIBuilder(private val config: Config) {

    /**
     * Constructs and returns a ready-to-display configuration UI.
     * The returned `ConfigUI` object contains a screen and lifecycle hooks.
     */
    fun build(): ConfigUI {
        return object : ConfigUI {

            // Main interactive screen driven by Elementa
            override val screen = object : WindowScreen(ElementaVersion.V2) {
                private val list = UIBlock()  // Background list container
                private val card = UIBlock()  // Main category content container
                private val title = UIText(config.name)

                private var selectedCategory: ConfigCategory? = config.categories.firstOrNull()
                private var isInitialized = false  // Ensures initial load happens only once

                override fun shouldPause(): Boolean = false

                init {
                    // === Frame Containers ===

                    list
                        .constrain {
                            width = 600.pixels()
                            height = 350.pixels()
                            x = CenterConstraint()
                            y = CenterConstraint()
                        }
                        .setColor(NovaPalette.Base.withAlpha(100))
                        .setChildOf(window)

                    card
                        .constrain {
                            width = 475.pixels()
                            height = 350.pixels()
                            x = CenterConstraint() + 63.pixels() // Offset to the right of list panel
                            y = CenterConstraint()
                        }
                        .setColor(NovaPalette.Base)
                        .setChildOf(window)

                    title
                        .constrain {
                            x = CenterConstraint() - 237.pixels()
                            y = CenterConstraint() - 160.pixels()
                        }
                        .setTextScale(1.5f.pixels())
                        .setChildOf(window)

                    // === Category Button Panel ===

                    val categoryLabels = mutableMapOf<ConfigCategory, UIComponent>()

                    config.categories.forEachIndexed { index, category ->
                        // Background shading for button
                        val buttonBG = UIRoundedRectangle(6f)
                            .constrain {
                                width = 100.pixels()
                                height = 20.pixels()
                                x = CenterConstraint() - 237.pixels()
                                y = CenterConstraint() - 120.pixels() + (index * 30).pixels()
                            }
                            .setColor(NovaPalette.Mauve)
                            .setChildOf(window)

                        // Actual button surface
                        val button = UIRoundedRectangle(6f)
                            .constrain {
                                width = 100.pixels()
                                height = 20.pixels()
                                x = CenterConstraint()
                                y = CenterConstraint() - 2.pixels()
                            }
                            .setColor(NovaPalette.Surface0)
                            .setChildOf(buttonBG)

                        // Category label text
                        val label = UIWrappedText(category.name)
                            .constrain {
                                x = CenterConstraint()
                                y = CenterConstraint()
                                width = 96.pixels()
                            }
                            .setColor(if (selectedCategory == category) NovaPalette.Mauve else Color.WHITE)
                            .setChildOf(button)

                        categoryLabels[category] = label

                        // Click handler to change category view
                        button.onMouseClick {
                            if (selectedCategory != category) {
                                selectedCategory = category

                                // Update label highlight colors
                                categoryLabels.forEach { (cat, lbl) ->
                                    lbl.setColor(if (cat == selectedCategory) NovaPalette.Mauve else Color.WHITE)
                                }

                                // Swap out current category panel
                                card.clearChildren()

                                if (category.isMarkdown) MarkdownCategoryUIBuilder().build(card, category)
                                else CategoryUIBuilder().build(card, config, category)
                            }
                        }
                    }

                    // Initial render of first category when screen is created
                    if (!isInitialized) {
                        selectedCategory?.let {
                            if (it.isMarkdown) MarkdownCategoryUIBuilder().build(card, it)
                            else CategoryUIBuilder().build(card, config, it)
                        }

                        isInitialized = true
                    }
                }
            }

            /**
             * Schedules the config UI to appear after one tick. Safe for use from any context.
             */
            override fun show() {
                TickScheduler.schedule(1) {
                    MinecraftClient.getInstance().setScreen(screen)
                }
            }

            /**
             * Defines what happens when the config UI should close.
             * Currently a placeholder—extend as needed.
             */
            override fun hide() {
                /* Implement hide logic here if needed */
            }
        }
    }
}

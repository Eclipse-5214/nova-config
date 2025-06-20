package co.eclipse5214.novaconfig.ui

import co.eclipse5214.novaconfig.model.Config
import co.eclipse5214.novaconfig.model.ConfigCategory
import co.eclipse5214.novaconfig.utils.TickScheduler
import co.eclipse5214.novaconfig.utils.chatutils
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import co.eclipse5214.novaconfig.ui.NovaPalette.withAlpha
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.UIComponent
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.universal.UMatrixStack
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.ScissorEffect
import net.minecraft.client.gui.DrawContext
import java.awt.Color

class ConfigUIBuilder(private val config: Config) {
    fun build(): ConfigUI {
        return object : ConfigUI {
            override val screen = object :  WindowScreen(ElementaVersion.V2) {
                private val list = UIBlock()
                private val card = UIBlock()
                private val title = UIText(config.name)
                private var currentSettings = config.flattenToMap()

                private fun refreshUI() {
                    selectedCategory?.let { category ->
                        currentSettings = config.flattenToMap()
                        card.clearChildren()
                        CategoryUIBuilder().build(card, category, currentSettings, ::refreshUI)
                    }
                }

                // Track selected category and initialization state
                var selectedCategory: ConfigCategory? = config.categories.firstOrNull()
                var isInitialized = false

                init {
                    // Background card
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
                            x = CenterConstraint() + 63.pixels()
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

                    val categoryLabels = mutableMapOf<ConfigCategory, UIComponent>()

                    // Build category buttons
                    config.categories.forEachIndexed { index, category ->
                        val buttonBG = UIRoundedRectangle(6f)
                            .constrain {
                                width = 100.pixels()
                                height = 20.pixels()
                                x = CenterConstraint() - 237.pixels()
                                y = CenterConstraint() - 120.pixels() + (index * 30).pixels()
                            }
                            .setColor(NovaPalette.Mauve)
                            .setChildOf(window)

                        val button = UIRoundedRectangle(6f)
                            .constrain {
                                width = 100.pixels()
                                height = 20.pixels()
                                x = CenterConstraint()
                                y = CenterConstraint() - 2.pixels()
                            }
                            .setColor(NovaPalette.Surface0)
                            .setChildOf(buttonBG)

                        val label = UIText(category.name)
                            .constrain {
                                x = CenterConstraint()
                                y = CenterConstraint()
                            }
                            .setColor(if (selectedCategory == category) NovaPalette.Mauve else Color.WHITE)
                            .setChildOf(button)

                        categoryLabels[category] = label

                        button.onMouseClick {
                            if (selectedCategory != category) {
                                selectedCategory = category
                                categoryLabels.forEach { (cat, lbl) ->
                                    lbl.setColor(if (cat == selectedCategory) NovaPalette.Mauve else Color.WHITE)
                                }
                                chatutils.clientMsg("§d[Nova] §b${category.name} pressed!", false)

                                // **Remove previous category UI**
                                card.clearChildren()

                                CategoryUIBuilder().build(card, category, currentSettings, ::refreshUI)
                            }
                        }
                    }

                    // **Build initial category UI only once when the screen opens**
                    if (!isInitialized) {
                        selectedCategory?.let { CategoryUIBuilder().build(card, it, currentSettings, ::refreshUI) }
                        isInitialized = true
                    }
                }
            }

            override fun show() {
                TickScheduler.schedule(1) {
                    MinecraftClient.getInstance().setScreen(screen)
                }
            }

            override fun hide() { /* Handle hiding logic */
            }
        }
    }
}
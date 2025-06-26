package co.stellarskys.novaconfig.ui.elements

import co.stellarskys.novaconfig.model.elements.Dropdown
import co.stellarskys.novaconfig.ui.NovaPalette
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.*

class DropdownUIBuilder {
    fun build(root: UIComponent, dropdown: Dropdown): UIComponent {
        val container = UIRoundedRectangle(6f)
            .constrain {
                width = 425.pixels()
                height = 60.pixels()
                x = CenterConstraint()
                y = PixelConstraint(20f)
            }
            .setColor(NovaPalette.Surface0)
            .setChildOf(root)

        renderCollapsed(container, dropdown)

        return container
    }

    fun renderCollapsed(wrapper: UIComponent, dropdown: Dropdown) {
        val selectedLabel = (dropdown.options.getOrNull(dropdown.value as Int) ?: "Select") + " ▼"

        val name = UIText(dropdown.name)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(5f)
            }
            .setChildOf(wrapper)

        val desc = UIText("§7" + dropdown.description)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(17f)
            }
            .setChildOf(wrapper)

        val optionContainer = UIRoundedRectangle(5f)
            .constrain {
                width = 75.pixels()
                height = 18.pixels()
                x = PixelConstraint(323f)
                y = CenterConstraint()
            }
            .setColor(NovaPalette.Surface2)
            .setChildOf(wrapper)

        val label = UIText(selectedLabel)
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setChildOf(optionContainer)

        optionContainer.onMouseClick {
            wrapper.constrain {
                height = if(dropdown.options.size <= 4) (dropdown.options.size * 18 + 26).pixels() else 98.pixels()
            }

            wrapper.clearChildren()
            renderExpanded(wrapper, dropdown)
        }
    }

    fun renderExpanded(wrapper: UIComponent, dropdown: Dropdown) {
        val selectedLabel = (dropdown.options.getOrNull(dropdown.value as Int) ?: "Select") + " ▲"

        val name = UIText(dropdown.name)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(5f)
            }
            .setChildOf(wrapper)

        val desc = UIText("§7" + dropdown.description)
            .constrain {
                x = PixelConstraint(7f)
                y = PixelConstraint(17f)
            }
            .setChildOf(wrapper)

        val optionContainer = UIRoundedRectangle(5f)
            .constrain {
                width = 75.pixels()
                height = 18.pixels()
                x = PixelConstraint(323f)
                y = PixelConstraint(2f)
            }
            .setColor(NovaPalette.Surface2)
            .setChildOf(wrapper)

        val label = UIText(selectedLabel)
            .constrain {
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setChildOf(optionContainer)

        val dropdownOptions = UIRoundedRectangle(5f)
            .constrain {
                width = 75.pixels()
                height = if(dropdown.options.size <= 4) (dropdown.options.size * 18 + 2).pixels() else 74.pixels
                x = PixelConstraint(323f)
                y = SiblingConstraint(2f)
            }
            .setColor(NovaPalette.Surface1)
            .setChildOf(wrapper)

        val dropdownScroller = ScrollComponent()
            .constrain {
                width = RelativeConstraint(1f)
                height = RelativeConstraint(1f)
                x = CenterConstraint()
                y = CenterConstraint()
            }
            .setChildOf(dropdownOptions)

        dropdown.options.forEachIndexed { index, option ->
            val enteyComtainer = UIRoundedRectangle(5f)
                .constrain {
                    width = 71.pixels()
                    height = 16.pixels()
                    x = CenterConstraint()
                    y = if (index == 0) PixelConstraint(2f) else SiblingConstraint(2f)
                }
                .setChildOf(dropdownScroller)
                .setColor(NovaPalette.Mauve)

            val entry = UIText(option)
                .constrain {
                    x = CenterConstraint()
                    y = CenterConstraint()
                }
                .setChildOf(enteyComtainer)

            enteyComtainer.onMouseClick {
                dropdown.value = index
                wrapper.constrain {
                    height = 60.pixels()
                }

                wrapper.clearChildren()
                renderCollapsed(wrapper, dropdown)
            }
        }
    }
}

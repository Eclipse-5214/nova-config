package co.stellarskys.novaconfig.core

import co.stellarskys.novaconfig.RGBA

open class ConfigCategory(val name: String) {
    val elements = mutableMapOf<String, ConfigElement>()
    var isMarkdown = false
    var markdown = ""

    fun button(builder: Button.() -> Unit) {
        val button = Button().apply(builder)
        elements[button.configName] = button
    }

    fun colorpicker(builder: ColorPicker.() -> Unit) {
        val color = ColorPicker().apply(builder)
        elements[color.configName] = color
    }

    fun dropdown(builder: Dropdown.() -> Unit) {
        val dropdown = Dropdown().apply(builder)
        elements[dropdown.configName] = dropdown
    }

    fun keybind(builder: Keybind.() -> Unit) {
        val keybind = Keybind().apply(builder)
        elements[keybind.configName] = keybind
    }

    fun slider(builder: Slider.() -> Unit) {
        val slider = Slider().apply(builder)
        elements[slider.configName] = slider
    }

    fun stepslider(builder: StepSlider.() -> Unit) {
        val step = StepSlider().apply(builder)
        elements[step.configName] = step
    }

    fun subcategory(configName: String) {
        val sub = Subcategory().apply {
            this.configName = configName
            this.name = configName.replaceFirstChar { it.uppercase() }
        }
        elements[configName] = sub
    }

    fun textinput(builder: TextInput.() -> Unit) {
        val input = TextInput().apply(builder)
        elements[input.configName] = input
    }

    fun textparagraph(builder: TextParagraph.() -> Unit) {
        val para = TextParagraph().apply(builder)
        elements[para.configName] = para
    }

    fun toggle(builder: Toggle.() -> Unit) {
        val toggle = Toggle().apply(builder)
        elements[toggle.configName] = toggle
    }

    // Listener functions ig
    fun Button.onclick(cb: () -> Unit) {
        this.onClick = cb
    }

    fun TextInput.onvaluechange(cb: (String) -> Unit) {
        this.onValueChanged = cb
    }
}

class MarkdownCategory(val mdName: String, val md: String): ConfigCategory(md){
    init {
        isMarkdown = true
        markdown = md
    }
}

open class ConfigElement {
    var configName: String = ""
    var name: String = ""
    var description: String = ""
    var value: Any? = null

    var showIf: ((Map<String, Any?>) -> Boolean)? = null

    fun shouldShow(predicate: (Map<String, Any?>) -> Boolean) {
        showIf = predicate
    }

    fun isVisible(config: Config): Boolean {
        val flat = config.flattenValues()
        return showIf?.invoke(flat) ?: true
    }
}

// Elements
class Button : ConfigElement() {
    var placeholder: String = "Click"
    var onClick: (() -> Unit)? = null
}

class ColorPicker : ConfigElement() {
    var default: RGBA = RGBA(255, 255, 255, 255)
        set(value) {
            field = value
            this.value = value
        }

    init {
        value = default
    }

    fun rgba(r: Int, g: Int, b: Int, a: Int): RGBA {
        return RGBA(r, g, b, a)
    }
}

class Dropdown : ConfigElement() {
    var options: List<String> = listOf()
    var default: Int = 0
        set(value) {
            field = value
            this.value = value
        }

    init {
        value = default
    }
}

class Keybind : ConfigElement() {
    var default: Int = 0
        set(value) {
            field = value
            this.value = value
        }

    init {
        value = default
    }
}

class Slider : ConfigElement() {
    var min: Float = 0f
    var max: Float = 1f
    var default: Float = 0.5f
        set(value) {
            field = value
            this.value = value
        }

    init {
        value = default
    }
}

class StepSlider : ConfigElement() {
    var min: Int = 0
    var max: Int = 10
    var step: Int = 1
    var default: Int = 0
        set(value) {
            field = value
            this.value = value
        }

    init {
        value = default
    }
}

class Subcategory : ConfigElement()

class TextInput : ConfigElement() {
    var placeholder: String = ""
        set(value) {
            field = value
            this.value = value
        }

    init {
        value = placeholder
    }

    var onValueChanged: ((String) -> Unit)? = null
}

class TextParagraph : ConfigElement()

class Toggle : ConfigElement() {
    var default: Boolean = false
        set(value) {
            field = value
            this.value = value
        }

    init {
        value = default
    }
}

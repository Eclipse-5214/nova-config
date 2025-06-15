package co.eclipse5214.novaconfig.ui

import java.awt.Color

object NovaPalette {
    private val theme: Theme = ThemeLoader.loadThemeOrNull("themes/frappe.json")

    val Surface0 get() = theme["Surface0"] ?: Color.decode("#000000")
    val Surface1 get() = theme["Surface1"] ?: Color.decode("#000000")
    val Text     get() = theme["Text"]     ?: Color.decode("#FFFFFF")
    val Accent   get() = theme["Accent"]   ?: Color.decode("#89B4FA")
}

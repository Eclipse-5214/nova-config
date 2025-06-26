package co.stellarskys.novaconfig.ui

import java.awt.Color
import kotlin.text.get

/**
 * NovaPalette – Unified theming system for NovaConfig.
 *
 * Inspired in full by designs and color guidance from Catppuccin.
 * https://github.com/catppuccin
 */
object NovaPalette {

    // Active theme (can be replaced with user-defined overrides)
    private var theme: Theme = Theme(emptyMap())

    /**
     * Initializes or replaces the current theme with a new one.
     * @param newTheme The theme to apply to the palette.
     */
    fun init(newTheme: Theme) {
        theme = newTheme
    }

    /**
     * Extension function to apply a custom alpha channel to an existing Color.
     * @param alpha Alpha value (0–255).
     * @return A new Color with the same RGB and specified alpha.
     */
    fun Color.withAlpha(alpha: Int): Color {
        return Color(red, green, blue, alpha)
    }

    // === Primary color palette ===

    val Rosewater get() = theme["Rosewater"] ?: Color.decode("#f2d5cf")
    val Flamingo  get() = theme["Flamingo"]  ?: Color.decode("#eebebe")
    val Pink      get() = theme["Pink"]      ?: Color.decode("#f4b8e4")
    val Mauve     get() = theme["Mauve"]     ?: Color.decode("#ca9ee6")
    val Red       get() = theme["Red"]       ?: Color.decode("#e78284")
    val Maroon    get() = theme["Maroon"]    ?: Color.decode("#ea999c")
    val Peach     get() = theme["Peach"]     ?: Color.decode("#ef9f76")
    val Yellow    get() = theme["Yellow"]    ?: Color.decode("#e5c890")
    val Green     get() = theme["Green"]     ?: Color.decode("#a6d189")
    val Teal      get() = theme["Teal"]      ?: Color.decode("#81c8be")
    val Sky       get() = theme["Sky"]       ?: Color.decode("#99d1db")
    val Sapphire  get() = theme["Sapphire"]  ?: Color.decode("#85c1dc")
    val Blue      get() = theme["Blue"]      ?: Color.decode("#8caaee")
    val Lavender  get() = theme["Lavender"]  ?: Color.decode("#babbf1")

    // === Foreground text and overlays ===

    val Text      get() = theme["Text"]      ?: Color.decode("#c6d0f5")
    val Subtext1  get() = theme["Subtext1"]  ?: Color.decode("#b5bfe2")
    val Subtext0  get() = theme["Subtext0"]  ?: Color.decode("#a5adce")
    val Overlay2  get() = theme["Overlay2"]  ?: Color.decode("#949cbb")
    val Overlay1  get() = theme["Overlay1"]  ?: Color.decode("#838ba7")
    val Overlay0  get() = theme["Overlay0"]  ?: Color.decode("#737994")

    // === Background surfaces ===

    val Surface2  get() = theme["Surface2"]  ?: Color.decode("#626880")
    val Surface1  get() = theme["Surface1"]  ?: Color.decode("#51576d")
    val Surface0  get() = theme["Surface0"]  ?: Color.decode("#414559")
    val Base      get() = theme["Base"]      ?: Color.decode("#303446")
    val Mantle    get() = theme["Mantle"]    ?: Color.decode("#292c3c")
    val Crust     get() = theme["Crust"]     ?: Color.decode("#232634")
}
<p align="center">
  <img alt="Nova logo" src="https://i.imgur.com/y0YX8WC.png" />
</p>

***
<p align="center">
  <a href="https://github.com/Eclipse-5214/nova-config/releases" target="_blank">
    <img alt="release" src="https://img.shields.io/github/v/release/Eclipse-5214/nova-config?color=3ba9ff&style=flat-square" />
  </a>
  <a href="https://github.com/Eclipse-5214/nova-config/releases" target="_blank">
    <img alt="downloads" src="https://img.shields.io/github/downloads/Eclipse-5214/nova-config/total?color=3ba9ff&style=flat-square" />
  </a>
  <a href="https://github.com/Eclipse-5214/nova-config/">
    <img src="https://tokei.rs/b1/github/Eclipse-5214/nova-config?category=code&color=3ba9ff&style=flat-square" alt="lines">
  </a>
</p>

<p align="center"><em>NovaConfig</em> is a high-level configuration system for mod developers—focused on expressiveness, modularity, and zero boilerplate.</p>

---

<h2 align="center">✨ Key Features</h2>

### **DSL-Based Config**
- Fully Kotlin DSL with rich builder syntax  
- Define categories, toggles, inputs, sliders, and more in one place  

### **Modular UI Components**
- Toggle, slider, color picker, dropdown, text input, keybind, and more  
- Expandable sections and subcategories  
- Smooth animations and built-in accessibility  

### **Live Reloading**
- Changes to config files apply instantly  
- No restarts or reload commands  

### **Built-in Markdown Docs**
- Add tutorial tabs or changelogs directly in your config screen  
- Great for onboarding players and contributors  

---

<h2 align="center">⚙️ Installation</h2>

Add NovaConfig as a dependency in your mod project. Gradle support coming soon™.

For now, clone and include NovaConfig as a module or use it as a dev bundle.

---

<h2 align="center">📦 Example Usage</h2>

```kotlin
val config = NovaApi.createConfig("example", "yourmodid") {
    category("General") {
        toggle {
            configName = "enabled"
            name = "Enable Feature"
            description = "Toggle a feature on or off"
            default = true
        }
        colorpicker {
            configName = "themeColor"
            name = "Theme Color"
            description = "Pick your favorite hue"
            default = rgba(255, 255, 255, 255)
        }
    }
}
```

for a full example look in the example folder

<h2 align="center">🧩 Accessing Config Values</h2>

Once a config is registered using NovaApi.createConfig(...), you can retrieve values like so:

```kotlin
val config = NovaApi["example"]
val isEnabled = config as Boolean ?: false
val themeColor = (config as RGBA).getColor("themeColor") ?: rgba(255, 255, 255, 255)
```

- you have to specify the type using `as`

> You can also listen for changes if you're implementing reactive behavior!


---

<h2 align="center">🙌 Credits</h2>

- **[Eclipse (NEXD_)]** – Architect, DSL designer, and UI conjurer  
- [Elementa](https://github.com/gg/elementa) – Fluent UI base  
- [Amaterasu](https://github.com/DocilElm/Amaterasu) – Config design inspiration  
- [Catppuccin](https://github.com/catppuccin) – Palette perfection  
- [Stella](https://github.com/Eclipse-5214/stella) – Mod I made this for
- Everyone contributing code, ideas, bug reports, or color feedback ✨
- And of course DrPepper!


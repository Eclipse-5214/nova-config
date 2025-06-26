§d# Welcome to NovaConfig!

§bNovaConfig lets you create customizable, modular, and downright ✨aesthetic✨ configs for your Fabric mods.  
This markdown category can be used for:
- §aMod descriptions
- §eChangelogs
- §6Setup guides
- §9Easter eggs 🥚

§d## 📜 Example: Button Logic

§7```kotlin
button {
    configName = "surprise_button"
    name = "Click Me!"
    onclick {
        chatutils.clientMsg("§d[Nova] §bYou discovered the easter egg!", false)
    }
}
§7```

§d## 🧪 Color Picker in Action

§7```kotlin
colorpicker {
    configName = "accent_color"
    name = "Nova Accent"
    default = rgba(195, 40, 118, 123)
}
§7```

§5Pro tip: You can use §nMinecraft formatting codes§r directly in names, descriptions, and now even Markdown sections!

§lStay bright, stay modular. NovaConfig out. ☄️

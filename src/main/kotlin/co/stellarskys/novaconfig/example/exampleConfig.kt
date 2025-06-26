package co.stellarskys.novaconfig.example

import co.stellarskys.novaconfig.NovaApi
import co.stellarskys.novaconfig.utils.FileUtils
import co.stellarskys.novaconfig.utils.chatutils
import java.awt.Desktop
import java.net.URI

// Main config definition for NovaConfig under the mod id "nova-config" named "example"
// This also has an optional file path parameter for save locations
// The default save location is .minecraft/config/$modId/settings.json
val myConfig = NovaApi.createConfig("example", "nova-config") {

    // General category with branding, link buttons, and welcome message
    category("General") {

        // Text paragraph element that introduces NovaConfig and credits me :D
        textparagraph {
            configName = "title"
            name = "§dNova Config Example :D"
            description = "§bWelcome to the Nova Config example config!\n" +
                    "§bNova Config has many wonderful features and this example config is " +
                    "§bdesigned to show them off!\n" +
                    "§bMade with <3 by NEXD_"
        }

        // Button to open the official website in the default browser
        button {
            configName = "website"
            name = "Website"
            description = "Link to the Nova Config website"
            onclick {
                try {
                    val uri = URI("https://nova.stellarskys.co")
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().browse(uri)
                    } else {
                        chatutils.clientMsg("§d[Nova] §bWebsite: $uri", false)
                    }
                } catch (e: Exception) {
                    println("Failed to open website: ${e.message}")
                }
            }
        }

        // Button linking to the documentation site
        button {
            configName = "docs"
            name = "Docs"
            description = "Link to the Nova Config docs"
            onclick {
                try {
                    val uri = URI("https://nova.stellarskys.co/docs")
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().browse(uri)
                    } else {
                        chatutils.clientMsg("§d[Nova] §bDocs: $uri", false)
                    }
                } catch (e: Exception) {
                    println("Failed to open website: ${e.message}")
                }
            }
        }

        // Button linking to the mod’s GitHub repo
        button {
            configName = "github"
            name = "Github"
            description = "Link to the Nova Config GitHub / source code"
            onclick {
                try {
                    val uri = URI("https://github.com/Eclipse-5214/nova-config")
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().browse(uri)
                    } else {
                        chatutils.clientMsg("§d[Nova] §Github: $uri", false)
                    }
                } catch (e: Exception) {
                    println("Failed to open website: ${e.message}")
                }
            }
        }
    }

    // Example category demonstrating all NovaConfig elements
    category("Example") {

        // Creates a subcategory (currently just visual/semantic)
        subcategory("General")

        // Another paragraph for showcasing long-form wrapped text
        textparagraph {
            configName = "GeneralTitle"
            name = "§dGeneral Settings"
            description = "Welcome to general settings for configuring general settings\n" +
                    "This is a really long line of text to show off text wrapping. " +
                    "Btw, the last line was split with a \\n. The FitnessGram Pacer Test... [etc.]"
        }

        // Boolean toggle with a default value of true
        toggle {
            configName = "do_something"
            name = "Do Something"
            description = "This does something"
            default = true
        }

        // Conditional toggle—only appears if "do_something" is enabled
        // shouldShow works for all elements except subcategories
        // it acts like an if statement so shouldShow{ true } would always show up
        toggle {
            configName = "Toggle"
            name = "Normal Toggle"
            description = "This is a normal toggle (Trust)"
            default = false

            shouldShow { it["do_something"] }
        }

        // RGBA Color picker with default white
        colorpicker {
            configName = "color_picker"
            name = "Fun Color Picker"
            description = "A cool color picker"
            default = rgba(255, 255, 255, 255)
        }

        // Button that shows a toast message when clicked
        button {
            configName = "button"
            name = "Cool Button"
            description = "A very cool button"
            onclick {
                // An internal function I'm using to help my sanity
                chatutils.clientMsg("§d[Nova] §bCool Button Pressed!", false)
            }
        }

        // Text input field that responds to user typing
        textinput {
            configName = "text_input"
            name = "Amazing Text Input"
            description = "This is an amazing Text Input"
            placeholder = "Type something here!"
            onvaluechange { string ->
                chatutils.clientMsg("§d[Nova] §bYou typed $string!", false)
            }
        }

        // Keybind input using key code, default is 80 (P key)
        keybind {
            configName = "key_bind"
            name = "Superb Key Bind"
            description = "A superb keybind"
            default = 80
        }

        // Basic dropdown menu with four options
        dropdown {
            configName = "drop_down"
            name = "Stellar Dropdown"
            description = "A stellar dropdown"
            options = listOf("option 1", "option 2", "option 3", "option 4")
        }

        // Slider between 5 and 10
        slider {
            configName = "slider"
            name = "Incredible Slider"
            description = "An incredible slider"
            min = 5f
            max = 10f
            default = 5f
        }

        // Step slider with fixed interval of 2
        stepslider {
            configName = "step_slider"
            name = "Breathtaking Step Slider"
            description = "A breathtaking step slider"
            min = 2
            max = 10
            step = 2
            default = 2
        }
    }

    // Custom markdown category rendered from a file using the FileUtils internal helper function (this can be literal text too)
    markdowncatagory(
        name = "Tutorial",
        markdown = FileUtils.loadMarkdown("example.md")
    )
}

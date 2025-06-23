package co.eclipse5214.novaconfig.example

import co.eclipse5214.novaconfig.NovaApi
import co.eclipse5214.novaconfig.utils.chatutils

val myConfig = NovaApi.createConfig("example") {
    category("General") {
        textparagraph {
            configName = "title"
            name = "§dNova Config Example :D"
            description = "§bwelcome to the Nova Config example config!\n" +
                          "§bNova Config has many wonderful features\n" +
                          "§band this example config is designed to\n" +
                          "§bshow them off!"
        }
    }

    category("Example") {
        subcategory("General")
        textparagraph {
            configName = "GeneralTitle"
            name = "§dGeneral Settings"
            description = "welcome to general settings for configuring general settings\n" +
                          "This is a reallly long line of text to show off text wrapping, btw the last line was split with a \\n. The FitnessGram Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds."
        }

        toggle {
            configName = "do_something"
            name = "Do Something"
            description = "This does something"
            default = true
        }

        toggle {
            configName = "something_else"
            name = "Do Something Else"
            description = "This doesn't something else"
            default = false

            shouldShow{ it["do_something"] }
        }

        colorpicker {
            configName = "color_picker"
            name = "Fun Color Picker"
            description = "A cool color picker"
            default = rgba(255,255,255,255)
        }

        button {
            configName = "button"
            name = "Cool Button"
            description = "A very cool button"

            onclick {
                chatutils.clientMsg("§d[Nova] §bCool Button Pressed!", false)
            }
        }

        textinput {
            configName = "text_input"
            name = "Amazing Text Input"
            description = "This is an amazing Text Input"
            placeholder = "Type something here!"

            onvaluechange { string ->
                chatutils.clientMsg("§d[Nova] §bYou typed $string!", false)
            }
        }

        keybind {
            configName = "key_bind"
            name = "Superb Key Bind"
            description = "A superb keybind"
            default = 80
        }
    }
}
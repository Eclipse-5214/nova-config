package co.eclipse5214.novaconfig.example

import co.eclipse5214.novaconfig.NovaApi

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
                          "This is a reallly long line of text to test text wrapping btw the last line was split with a \\n. The FitnessGram Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds."
        }

        toggle {
            configName = "show_map"
            name = "Do Something"
            description = "This does something"
            default = true
        }

        toggle {
            configName = "something_else"
            name = "Do Something Else"
            description = "This does something else"
            default = false
        }

        textparagraph {
            configName = "GeneralTitle"
            name = "§dGeneral Settings"
            description = "welcome to general settings for configuring general settings\n" +
                    "This is a reallly long line of text to test text wrapping btw the last line was split with a \\n. The FitnessGram Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds."
        }

        toggle {
            configName = "a_third_thing"
            name = "A Third Thing"
            description = "This does a third thing"
            default = true
        }

    }
}
package co.eclipse5214.novaconfig.example

import co.eclipse5214.novaconfig.NovaApi

val myConfig = NovaApi.createConfig("example") {
    category("General") {
        toggle {
            configName = "show_map"
            name = "Show Map"
            description = "Toggle the dungeon overlay"
            default = true
        }
    }
}
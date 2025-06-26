package co.stellarskys.novaconfig.utils

import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

object chatutils {
    @JvmStatic

    fun clientMsg(message: String, addHud: Boolean) {
        val client = MinecraftClient.getInstance()
        if (addHud) {
            client.inGameHud.chatHud.addMessage(Text.literal(message))
        } else {
            client.player?.sendMessage(Text.literal(message), false)
        }
    }

    @JvmStatic
    fun sendMsg(message: String) {
        MinecraftClient.getInstance().player?.networkHandler?.sendChatMessage(message)
    }

    @JvmStatic
    fun sendCmd(command: String) {
        MinecraftClient.getInstance().player?.networkHandler?.sendChatCommand(command)
    }

    @JvmStatic
    fun removeFormatting(text: String): String {
        return text.replace(Regex("§[0-9a-fk-or]"), "")
    }
}


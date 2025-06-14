package co.eclipse5214.novaconfig

import co.eclipse5214.novaconfig.utils.chatutils
import com.mojang.brigadier.Command
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.MinecraftClient


object NovaConfig : ClientModInitializer {
	override fun onInitializeClient() {
		ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
			val cmd = Command<FabricClientCommandSource> { context ->
				//val client = MinecraftClient.getInstance()
				//client.execute {
				//	client.setScreen(GUI())
				//}
				chatutils.clientMsg("§d[Nova] §bCommand executed", false)

				1
			}
			dispatcher.register(ClientCommandManager.literal("nova-test").executes(cmd))
		}
	}
}
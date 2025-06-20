package co.eclipse5214.novaconfig

import co.eclipse5214.novaconfig.utils.chatutils
import com.mojang.brigadier.Command
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.MinecraftClient
import co.eclipse5214.novaconfig.example.myConfig
import co.eclipse5214.novaconfig.utils.TickScheduler
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Paths


object NovaConfig : ClientModInitializer {
	override fun onInitializeClient() {
		TickScheduler.register()

		ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
			val cmd = Command<FabricClientCommandSource> { context ->
				myConfig.open()

				chatutils.clientMsg("§d[Nova] §bValue of show_map is ${myConfig["show_map"]}", false)

				1
			}
			dispatcher.register(ClientCommandManager.literal("nova-test").executes(cmd))
		}
	}
}
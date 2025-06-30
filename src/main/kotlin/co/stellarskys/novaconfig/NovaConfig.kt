package co.stellarskys.novaconfig

import com.mojang.brigadier.Command
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import co.stellarskys.novaconfig.example.myConfig
import co.stellarskys.novaconfig.utils.TickScheduler

object NovaConfig : ClientModInitializer {
	override fun onInitializeClient() {
		// Start the tick scheduler
		TickScheduler.register()

		// Register super secret command
		ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
			val cmd = Command<FabricClientCommandSource> { context ->
				myConfig.open()
				1
			}

			dispatcher.register(
				ClientCommandManager.
				literal("nova-example").
				executes(cmd)
			)
		}
	}
}
package co.stellarskys.novaconfig

import co.stellarskys.novaconfig.event.DefaultEvents
import co.stellarskys.novaconfig.event.Event
import com.mojang.brigadier.Command
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import co.stellarskys.novaconfig.example.myConfig
import co.stellarskys.novaconfig.utils.TickScheduler
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext


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

		// save configs on game close
		ClientLifecycleEvents.CLIENT_STOPPING.register { _ ->
			NovaApi.saveAllConfigs()
		}

		// load default events
		DefaultEvents

		Event("world_render"){ cbx : WorldRenderContext ->
			println("hello from custom event")
		}.register()
	}
}
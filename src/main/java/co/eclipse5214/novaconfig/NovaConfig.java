package co.eclipse5214.novaconfig;

import co.eclipse5214.novaconfig.core.GUI;
import co.eclipse5214.novaconfig.utils.TickScheduler;
import co.eclipse5214.novaconfig.utils.chatutils;
import com.mojang.brigadier.Command;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NovaConfig implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			Command<FabricClientCommandSource> cmd = context -> {
				chatutils.clientmsg("§d[Nova] §bCommand Executed", false);

				TickScheduler.schedule(1, () ->{
					var client = MinecraftClient.getInstance();
					client.execute(() -> {
						client.setScreen(new GUI(Text.empty()));
					});
				});
				return 1;
			};

			dispatcher.register(ClientCommandManager.literal("nova-test").executes(cmd));
		});
	}
}
package co.stellarskys.novaconfig.event

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents

object DefaultEvents {
    val WorldRender = EventBus.createEvent<WorldRenderContext>("world_render") {
        WorldRenderEvents.LAST.register { ctx ->
            this.fire(ctx)
        }
    }

}
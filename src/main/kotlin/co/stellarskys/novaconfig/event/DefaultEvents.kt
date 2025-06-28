package co.stellarskys.novaconfig.event

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents

object DefaultEvents {
    // Register a custom event bound to an external hook (e.g., Fabric’s WorldRenderEvents)
    init {
        Events.createEvent("world_render") { cb ->
            val hook: (WorldRenderContext) -> Unit = { ctx ->
                cb(ctx)
            }

            WorldRenderEvents.LAST.register(hook)
            EventHandle { /* Fabric’s unregister not available, so you could no-op */ }
        }
    }


}
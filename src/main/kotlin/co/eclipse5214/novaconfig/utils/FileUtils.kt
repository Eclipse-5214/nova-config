package co.eclipse5214.novaconfig.utils

import net.fabricmc.loader.api.ModContainer
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier
import kotlin.io.path.name

object FileUtils {
    fun loadMarkdown(resourcePath: String): String {
        val markdown = Identifier.of("nova-api", "markdown/$resourcePath")
        //return markdownFile
        return MinecraftClient.getInstance()
            .resourceManager
            .openAsReader(markdown)
            .use { it.readText() }
    }
}
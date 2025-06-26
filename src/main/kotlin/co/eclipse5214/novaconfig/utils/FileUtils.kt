package co.eclipse5214.novaconfig.utils

import net.fabricmc.loader.api.ModContainer
import net.fabricmc.loader.api.FabricLoader
import java.io.File

object FileUtils {
    private val modContainer: ModContainer? = FabricLoader.getInstance()
        .getModContainer("nova-config")
        .orElseThrow { IllegalStateException("Mod not found") }

    private val rootPath = modContainer?.findPath("/")?.orElseThrow()

    fun loadMarkdown(resourcePath: String): File? {
        val markdownFile = rootPath
            ?.resolve("assets/nova-config/markdown/guide.md")
            ?.toFile()

        return markdownFile
    }
}
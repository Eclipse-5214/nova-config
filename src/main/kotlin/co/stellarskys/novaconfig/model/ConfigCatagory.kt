package co.stellarskys.novaconfig.model

data class ConfigCategory(
    val name: String,
    val elements: List<ConfigElement>,
    val isMarkdown: Boolean,
    val markdown: String? = null
)
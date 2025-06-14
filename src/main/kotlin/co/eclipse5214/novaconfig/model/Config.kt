package co.eclipse5214.novaconfig.model

data class Config(
    val name: String,
    val categories: List<ConfigCategory>
)
package co.eclipse5214.novaconfig.model

import co.eclipse5214.novaconfig.model.elements.Toggle

data class Config(
    val name: String,
    val categories: List<ConfigCategory>
) {
    fun flattenToMap(): Map<String, Any?> {
        val result = mutableMapOf<String, Any?>()

        for (category in categories) {
            for (element in category.elements) {
                when (element) {
                    is Toggle -> result[element.configName] = element.value
                    // other types could go here later
                }
            }
        }

        return result
    }
}

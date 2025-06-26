package co.stellarskys.novaconfig.model

import co.stellarskys.novaconfig.model.elements.RGBA
import co.stellarskys.novaconfig.model.elements.Toggle
import kotlinx.serialization.json.*

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

    fun toJson(): JsonObject {
        return buildJsonObject {
            categories.forEach { category ->
                val categoryValues = buildJsonObject {
                    category.elements.forEach { element ->
                        val id = element.id
                        val value = element.value

                        if (id != null && value != null) {
                            println("$id, $value")

                            val jsonValue = when (value) {
                                is Boolean -> JsonPrimitive(value)
                                is Int -> JsonPrimitive(value)
                                is Float -> JsonPrimitive(value)
                                is Double -> JsonPrimitive(value)
                                is String -> JsonPrimitive(value)
                                is RGBA -> JsonPrimitive(value.toHex())
                                else -> {
                                    println("Unsupported type for $id: ${value::class.simpleName}")
                                    return@forEach
                                }
                            }

                            put(id, jsonValue)
                        }
                    }
                }

                if (categoryValues.isNotEmpty()) {
                    put(category.name, categoryValues)
                }
            }
        }
    }

    fun fromJson(json: JsonObject) {
        for (category in categories) {
            val categoryData = json[category.name]?.jsonObject ?: continue

            for (element in category.elements) {
                val id = element.id ?: continue
                val jsonValue = categoryData[id] ?: continue

                val newValue = when (val current = element.value) {
                    is Boolean     -> jsonValue.jsonPrimitive.booleanOrNull
                    is Int         -> jsonValue.jsonPrimitive.intOrNull
                    is Float       -> jsonValue.jsonPrimitive.floatOrNull
                    is Double      -> jsonValue.jsonPrimitive.doubleOrNull
                    is String      -> jsonValue.jsonPrimitive.contentOrNull
                    is RGBA -> jsonValue.jsonPrimitive.contentOrNull?.let { RGBA.Companion.fromHex(it) }
                    else -> {
                        println("Skipping unsupported load type for '$id': ${current?.let { it::class.simpleName } ?: "null"}")
                        null
                    }
                }

                if (newValue != null) element.value = newValue
            }
        }
    }
}

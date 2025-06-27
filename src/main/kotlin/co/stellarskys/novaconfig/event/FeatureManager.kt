package co.stellarskys.novaconfig.event

import co.stellarskys.novaconfig.model.Config

class FeatureManager internal constructor(
    val config: Config
){
    private val features = mutableListOf<Feature>()

    init {

    }


    fun createFeature(configID: String): Feature {
        val feature = Feature(configID)
        features += feature
        return feature
    }

    private fun attachListener() {
        config.registerListener { configName, value ->

        }
    }

    fun hookRenderEvents() {
        EventB
    }

}
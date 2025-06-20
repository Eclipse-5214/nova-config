package co.eclipse5214.novaconfig.builder

abstract class ElementBuilder {
    var shouldShow: (Map<String, Any?>) -> Boolean = { true }

    fun shouldShow(predicate: (Map<String, Any?>) -> Any?) {
        shouldShow = { settings -> predicate(settings) == true }
    }
}

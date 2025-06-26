package co.stellarskys.novaconfig.builder

/**
 * Base class for all NovaConfig element builders that support conditional rendering.
 *
 * Subclasses can define interactive elements such as toggles, sliders, inputs, etc.,
 * and conditionally show or hide them based on the current config state.
 */
abstract class ElementBuilder {

    /**
     * A predicate function that determines whether this element should be shown.
     *
     * The map passed into the predicate contains the current config state, with keys being
     * `configName`s and values being the current settings values.
     *
     * Defaults to always returning true (i.e., always visible).
     */
    var shouldShow: (Map<String, Any?>) -> Boolean = { true }

    /**
     * Allows setting a custom conditional visibility predicate.
     *
     * This overload accepts a more lenient predicate that returns any value.
     * Internally, the result is coerced to a Boolean check with `== true`,
     * making it convenient to write concise DSL logic without manually returning a Boolean.
     *
     * Example:
     * ```
     * shouldShow { it["featureEnabled"] }
     * ```
     *
     * @param predicate A function that receives the settings map and returns a Boolean-ish value.
     */
    fun shouldShow(predicate: (Map<String, Any?>) -> Any?) {
        shouldShow = { settings -> predicate(settings) == true }
    }
}
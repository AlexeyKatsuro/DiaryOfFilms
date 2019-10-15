package com.alexeykatsuro.inputfromutil

import com.alexeykatsuro.inputfromutil.validation.Approver


typealias OnStateChanged<T> = (newState: T) -> Unit
typealias StateProvider<S> = () -> S
typealias ErrorReducer <S> = S.(error: String?) -> S


class Form<S>(private val stateProvider: StateProvider<S>) {

    private val itemMap: MutableMap<String, GenericFormItem<S, *>> = mutableMapOf()
    private var onStateChanged: OnStateChanged<S>? = null

    fun <T> withProp(
        propProvider: (S) -> T,
        reducer: ErrorReducer<S>,
        name: String? = null,
        setup: Approver<T>.() -> Unit
    ) {
        val key = name ?: propProvider.toString()
        if (itemMap.contains(key)) {
            @Suppress("UNCHECKED_CAST")
            val old: GenericFormItem<S, T> = itemMap[key]!! as GenericFormItem<S, T>
            itemMap[key] = GenericFormItem(propProvider, old.approver.also(setup), reducer)
        } else {
            itemMap[key] = GenericFormItem(propProvider, Approver<T>().also(setup), reducer)
        }
    }

    fun <T> withNamedProp(
        name: String,
        setup: Approver<T>.() -> Unit
    ) {
        @Suppress("UNCHECKED_CAST")
        val old: GenericFormItem<S, T> = itemMap[name] as GenericFormItem<S, T>?
            ?: throw IllegalStateException("FormItem with $name doesn't exist in from")

        itemMap[name] = GenericFormItem(old.input, old.approver.also(setup), old.reducer)
    }

    fun withState(reducer: ErrorReducer<S>, setup: Approver<S>.() -> Unit) {
        val key = stateProvider.toString() + reducer.toString()
        itemMap[key] = GenericFormItem({ stateProvider() }, Approver<S>().also(setup), reducer)
    }

    fun onStateChange(onChanged: OnStateChanged<S>) {
        onStateChanged = onChanged
    }

    fun validate(silent: Boolean = false): Boolean {
        var updatedState: S? = null
        val state = stateProvider.invoke()

        var result = true
        itemMap.values.forEach { item ->

            val fieldResult = item.verify(state)
            val errorMessage = fieldResult.errorMessage
            if (!silent) {
                val reducer = item.reducer
                updatedState = (updatedState ?: state).reducer(errorMessage)
            }

            result = fieldResult.isValid && result
        }

        updatedState?.let {
            onStateChanged?.invoke(it)
                ?: throw IllegalStateException("Doesn't assigned 'onStateChange' behavior")
        }
        return result
    }
}
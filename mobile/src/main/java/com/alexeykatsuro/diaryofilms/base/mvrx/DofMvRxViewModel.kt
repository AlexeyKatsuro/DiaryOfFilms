package com.alexeykatsuro.diaryofilms.base.mvrx

import com.airbnb.mvrx.*
import com.alexeykatsuro.diaryofilms.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

open class DofMvRxViewModel<S : MvRxState>(
    initialState: S
) : BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG) {

    protected suspend inline fun <T> Flow<T>.execute(
        crossinline reducer: S.(Async<T>) -> S
    ) = execute({ it }, reducer)

    protected suspend inline fun <T, V> Flow<T>.execute(
        crossinline mapper: (T) -> V,
        crossinline reducer: S.(Async<V>) -> S
    ) = map { Success(mapper(it)) as Async<V> }
        .catch { error ->
            emit(Fail(error))
        }.collect {
            setState { reducer(it) }
        }

}
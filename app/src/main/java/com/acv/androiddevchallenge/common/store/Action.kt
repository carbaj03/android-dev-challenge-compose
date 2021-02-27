package com.acv.androiddevchallenge.common.store

import androidx.lifecycle.viewModelScope
import com.acv.androiddevchallenge.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface Action

interface State

interface ViewStore<B : State> {
    fun B.render()
}

interface FormStore<A : State> {
    fun init(): StateFlow<A>
}

@OptIn(ExperimentalCoroutinesApi::class)
abstract class Store<A : Action, B : State>(
    initialState: B
) : FormStore<B>, ActionHandler<A>, SideEffects<A, B>, BaseViewModel() {

    private val state: MutableStateFlow<B> = MutableStateFlow(initialState)

    override fun init(): StateFlow<B> {
        onInit()
        return state
    }

    abstract fun onInit()

    override operator fun A.invoke() {
        val newState: B = reduce(state.value)
        newState.state()
        sideEffects(newState)
    }

    abstract fun A.reduce(currentState: B): B

    private fun B.state() {
        state.value = this
    }

    fun onCreate(view: ViewStore<B>) {
        viewModelScope.launch {
            init().collect { state ->
                view.run { state.render() }
            }
        }
    }

    fun onCreate(): StateFlow<B> =
        init()

    fun <C> launch(f: suspend () -> C, onSuccess: (C) -> Unit) {
        viewModelScope.launch {
            val a = withContext(Dispatchers.IO) { f() }
            onSuccess(a)
        }
    }
}

interface SideEffects<A : Action, B : State> {
    fun A.sideEffects(currentState: B)
}

interface ActionHandler<A : Action> {
    operator fun A.invoke()
}

interface EmptyState<A : State> {
    fun empty(): A
}
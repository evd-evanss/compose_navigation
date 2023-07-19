package com.sugarspoon.commons.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T: UiStates, in E: UiEvents>(initial: T): ViewModel() {

    private val _state: MutableStateFlow<T> = MutableStateFlow(initial)

    val state: StateFlow<T>
        get() = _state

    fun emitEvent(vararg events: E) {
        events.forEach { handler(_state.value, it) }
    }

    fun createNewState(newState: T) {
        _state.tryEmit(newState)
    }

    abstract fun handler(oldState: T, events: E)
}

interface UiEvents

interface UiStates
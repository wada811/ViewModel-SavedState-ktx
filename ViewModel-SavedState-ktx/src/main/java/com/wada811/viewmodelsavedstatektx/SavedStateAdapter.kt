package com.wada811.viewmodelsavedstatektx

interface SavedStateAdapter<TValue, TState> {
    fun toSavedState(value: TValue): TState
    fun fromSavedState(state: TState): TValue
}

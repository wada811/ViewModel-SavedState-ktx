package com.wada811.viewmodelsavedstate

interface SavedStateAdapter<TValue, TState> {
    fun toSavedState(value: TValue): TState
    fun fromSavedState(state: TState): TValue
}
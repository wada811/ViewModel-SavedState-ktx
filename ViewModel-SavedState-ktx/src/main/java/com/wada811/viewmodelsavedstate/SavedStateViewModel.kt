package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

abstract class SavedStateViewModel(
    private val _savedStateHandle: SavedStateHandle
) : ViewModel(), SavedStateHandler {
    override val <T : SavedStateHandler> T.savedStateHandle: SavedStateHandle
        get() = _savedStateHandle

    protected inline fun <reified T> SavedStateHandle.property(): ReadWriteProperty<SavedStateViewModel, T> = property(T::class.java)
    protected inline fun <reified T> SavedStateHandle.liveData(): ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> = liveData(T::class.java)
}

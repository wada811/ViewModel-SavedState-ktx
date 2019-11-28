package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

@Suppress("unused")
abstract class SavedStateViewModel(
    private val _savedStateHandle: SavedStateHandle
) : ViewModel(), ISavedStateViewModel {
    override val <T : ISavedStateViewModel> T.savedStateHandle: SavedStateHandle
        get() = _savedStateHandle

    protected inline fun <reified T> savedStateProperty(key: String? = null): ReadWriteProperty<SavedStateViewModel, T> {
        return savedStateProperty(T::class.java, key)
    }

    protected inline fun <reified T> savedStateLiveData(key: String? = null): ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {
        return savedStateLiveData(T::class.java, key)
    }
}

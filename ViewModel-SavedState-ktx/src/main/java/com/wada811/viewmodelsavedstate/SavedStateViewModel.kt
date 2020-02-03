package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wada811.viewmodelsavedstate.property.ReadOnlyPropertyProvider
import com.wada811.viewmodelsavedstate.property.ReadWritePropertyProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

abstract class SavedStateViewModel(
    @Suppress("unused") private val savedStateHandle: SavedStateHandle
) : ViewModel(), SavedStateHandler {
    @Suppress("DEPRECATION")
    protected inline fun <reified T> SavedStateHandle.property(): ReadWriteProperty<SavedStateViewModel, T> {
        return property(T::class.java)
    }

    @Suppress("DEPRECATION")
    protected inline fun <reified T> SavedStateHandle.property(initialValue: T): ReadWritePropertyProvider<SavedStateViewModel, T> {
        return property(T::class.java, initialValue)
    }

    @Suppress("DEPRECATION")
    protected inline fun <reified T> SavedStateHandle.liveData(): ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {
        return liveData(T::class.java)
    }

    @Suppress("DEPRECATION")
    protected inline fun <reified T> SavedStateHandle.liveData(initialValue: T): ReadOnlyPropertyProvider<SavedStateViewModel, MutableLiveData<T>> {
        return liveData(T::class.java, initialValue)
    }
}

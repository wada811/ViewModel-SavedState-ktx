package com.wada811.viewmodelsavedstate.delegatedproperty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.ISavedStateViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class SavedStateLiveDataProperty<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String?
) : ReadOnlyProperty<ISavedStateViewModel, MutableLiveData<T>> {
    override fun getValue(thisRef: ISavedStateViewModel, property: KProperty<*>): MutableLiveData<T> {
        return savedStateHandle.getLiveData(key ?: property.name)
    }
}

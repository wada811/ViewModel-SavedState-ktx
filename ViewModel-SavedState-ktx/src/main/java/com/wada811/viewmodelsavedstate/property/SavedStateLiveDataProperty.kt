package com.wada811.viewmodelsavedstate.property

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.SavedStateHandler
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class SavedStateLiveDataProperty<T>(
    private val savedStateHandle: SavedStateHandle
) : ReadOnlyProperty<SavedStateHandler, MutableLiveData<T>> {
    override fun getValue(thisRef: SavedStateHandler, property: KProperty<*>): MutableLiveData<T> {
        return savedStateHandle.getLiveData(property.name)
    }
}

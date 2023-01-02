package com.wada811.viewmodelsavedstatektx.property

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class SavedStateLiveDataProperty<T>(
    private val savedStateHandle: SavedStateHandle
) : ReadOnlyProperty<ViewModel, MutableLiveData<T>> {
    override fun getValue(thisRef: ViewModel, property: KProperty<*>): MutableLiveData<T> {
        return savedStateHandle.getLiveData(property.name)
    }
}

package com.wada811.viewmodelsavedstate.delegatedproperty

import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.ISavedStateViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class SavedStateProperty<T>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String?
) : ReadWriteProperty<ISavedStateViewModel, T> {

    @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
    override operator fun getValue(thisRef: ISavedStateViewModel, property: KProperty<*>): T {
        return savedStateHandle.get<T>(key ?: property.name) as T
    }

    override operator fun setValue(thisRef: ISavedStateViewModel, property: KProperty<*>, value: T) {
        savedStateHandle.set(key ?: property.name, value)
    }
}

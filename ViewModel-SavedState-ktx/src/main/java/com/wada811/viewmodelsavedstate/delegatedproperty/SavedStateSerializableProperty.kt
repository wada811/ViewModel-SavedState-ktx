package com.wada811.viewmodelsavedstate.delegatedproperty

import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.ISavedStateViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class SavedStateSerializableProperty<T, R>(
    private val savedStateHandle: SavedStateHandle,
    private val key: String?,
    private val deserialize: (T) -> R,
    private val serialize: (R) -> T
) : ReadWriteProperty<ISavedStateViewModel, R> {
    @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
    override operator fun getValue(thisRef: ISavedStateViewModel, property: KProperty<*>): R {
        return deserialize(savedStateHandle.get<T>(key ?: property.name) as T)
    }

    override operator fun setValue(thisRef: ISavedStateViewModel, property: KProperty<*>, value: R) {
        savedStateHandle.set(key ?: property.name, serialize(value))
    }
}

package com.wada811.viewmodelsavedstate.property

import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.SavedStateHandler
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class SavedStateSerializableProperty<T, R>(
    private val savedStateHandle: SavedStateHandle,
    private val deserialize: (T) -> R,
    private val serialize: (R) -> T
) : ReadWriteProperty<SavedStateHandler, R> {
    @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
    override operator fun getValue(thisRef: SavedStateHandler, property: KProperty<*>): R {
        return deserialize(savedStateHandle.get<T>(property.name) as T)
    }

    override operator fun setValue(thisRef: SavedStateHandler, property: KProperty<*>, value: R) {
        savedStateHandle.set(property.name, serialize(value))
    }
}

package com.wada811.viewmodelsavedstate.property

import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.SavedStateHandler
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class SavedStateProperty<T>(
    private val savedStateHandle: SavedStateHandle
) : ReadWriteProperty<SavedStateHandler, T> {
    @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
    override operator fun getValue(thisRef: SavedStateHandler, property: KProperty<*>): T {
        return savedStateHandle.get<T>(property.name) as T
    }

    override operator fun setValue(thisRef: SavedStateHandler, property: KProperty<*>, value: T) {
        savedStateHandle.set(property.name, value)
    }
}

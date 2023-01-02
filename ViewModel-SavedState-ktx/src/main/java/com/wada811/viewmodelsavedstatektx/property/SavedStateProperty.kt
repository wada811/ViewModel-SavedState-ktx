package com.wada811.viewmodelsavedstatektx.property

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class SavedStateProperty<T>(
    private val savedStateHandle: SavedStateHandle
) : ReadWriteProperty<ViewModel, T> {
    @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
    override operator fun getValue(thisRef: ViewModel, property: KProperty<*>): T {
        return savedStateHandle.get<T>(property.name) as T
    }

    override operator fun setValue(thisRef: ViewModel, property: KProperty<*>, value: T) {
        savedStateHandle[property.name] = value
    }
}

package com.wada811.viewmodelsavedstatektx.property

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wada811.viewmodelsavedstatektx.SavedStateAdapter
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class SavedStateSerializableProperty<TValue, TState>(
    private val savedStateHandle: SavedStateHandle,
    private val adapter: SavedStateAdapter<TValue, TState>
) : ReadWriteProperty<ViewModel, TValue> {
    @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
    override operator fun getValue(thisRef: ViewModel, property: KProperty<*>): TValue {
        return adapter.fromSavedState(savedStateHandle.get<TState>(property.name) as TState)
    }

    override operator fun setValue(thisRef: ViewModel, property: KProperty<*>, value: TValue) {
        savedStateHandle[property.name] = adapter.toSavedState(value)
    }
}

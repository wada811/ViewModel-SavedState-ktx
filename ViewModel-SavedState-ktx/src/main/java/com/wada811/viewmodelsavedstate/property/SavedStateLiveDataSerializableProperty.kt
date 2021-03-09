package com.wada811.viewmodelsavedstate.property

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wada811.viewmodelsavedstate.SavedStateAdapter
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class SavedStateLiveDataSerializableProperty<TValue, TState>(
    private val savedStateHandle: SavedStateHandle,
    private val adapter: SavedStateAdapter<TValue, TState>
) : ReadOnlyProperty<ViewModel, MutableLiveData<TValue>> {
    private var savedStateLiveData: MutableLiveData<TValue>? = null

    override fun getValue(thisRef: ViewModel, property: KProperty<*>): MutableLiveData<TValue> {
        return savedStateLiveData ?: SavedStateSerializableLiveData(savedStateHandle, property.name, adapter).also {
            savedStateLiveData = it
        }
    }

    @Suppress("UNCHECKED_CAST")
    private class SavedStateSerializableLiveData<TValue, TState>(
        savedStateHandle: SavedStateHandle,
        key: String,
        private val adapter: SavedStateAdapter<TValue, TState>
    ) : MediatorLiveData<TValue>() {
        private val liveData: MutableLiveData<TState> = savedStateHandle.getLiveData(key)

        init {
            value = adapter.fromSavedState(liveData.value as TState)
            addSource(liveData) {
                value = adapter.fromSavedState(it as TState)
            }
        }

        override fun getValue(): TValue {
            return adapter.fromSavedState(liveData.value as TState)
        }

        override fun setValue(value: TValue) {
            super.setValue(value)
            val stateValue = adapter.toSavedState(value)
            if (liveData.value != stateValue) {
                liveData.value = stateValue
            }
        }
    }
}

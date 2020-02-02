package com.wada811.viewmodelsavedstate.property

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.SavedStateHandler
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class SavedStateLiveDataSerializableProperty<T, R>(
    private val savedStateHandle: SavedStateHandle,
    private val deserialize: (T) -> R,
    private val serialize: (R) -> T
) : ReadOnlyProperty<SavedStateHandler, MutableLiveData<R>> {
    private var savedStateLiveData: MutableLiveData<R>? = null

    override fun getValue(thisRef: SavedStateHandler, property: KProperty<*>): MutableLiveData<R> {
        if (savedStateLiveData == null) {
            savedStateLiveData = SavedStateSerializableLiveData(savedStateHandle, property.name, deserialize, serialize)
        }
        return savedStateLiveData!!
    }

    @Suppress("UNCHECKED_CAST")
    private class SavedStateSerializableLiveData<T, R>(
        savedStateHandle: SavedStateHandle,
        key: String,
        private val deserialize: (T) -> R,
        private val serialize: (R) -> T
    ) : MediatorLiveData<R>() {
        private val liveData: MutableLiveData<T> = savedStateHandle.getLiveData<T>(key)

        init {
            value = deserialize(liveData.value as T)
            addSource(liveData) {
                value = deserialize(it as T)
            }
        }

        override fun getValue(): R {
            return deserialize(liveData.value as T)
        }

        override fun setValue(value: R) {
            super.setValue(value)
            val serializedValue = serialize(value)
            if (liveData.value != serializedValue) {
                liveData.value = serializedValue
            }
        }
    }
}
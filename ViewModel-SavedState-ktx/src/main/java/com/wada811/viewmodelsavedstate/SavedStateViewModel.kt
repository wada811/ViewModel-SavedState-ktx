package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("unused")
abstract class SavedStateViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    protected fun <T> savedStateProperty(key: String? = null): ReadWriteProperty<SavedStateViewModel, T> {
        return SavedStateProperty(savedStateHandle, key)
    }

    protected fun <T> savedStateLiveData(key: String? = null): ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {
        return SavedStateLiveData(savedStateHandle, key)
    }

    internal class SavedStateProperty<T>(
        private val savedStateHandle: SavedStateHandle,
        private val key: String? = null
    ) : ReadWriteProperty<SavedStateViewModel, T> {
        @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
        override fun getValue(thisRef: SavedStateViewModel, property: KProperty<*>): T {
            return savedStateHandle.get<T>(key ?: property.name) as T
        }

        override fun setValue(thisRef: SavedStateViewModel, property: KProperty<*>, value: T) {
            savedStateHandle.set(key ?: property.name, value)
        }
    }

    internal class SavedStateLiveData<T>(
        private val savedStateHandle: SavedStateHandle,
        private val key: String? = null
    ) : ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {
        override fun getValue(thisRef: SavedStateViewModel, property: KProperty<*>): MutableLiveData<T> {
            return savedStateHandle.getLiveData(key ?: property.name)
        }
    }

    companion object {
        private const val KEY_TEXT = "text"
    }
}

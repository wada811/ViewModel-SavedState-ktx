package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.delegatedproperty.SavedStateLiveDataProperty
import com.wada811.viewmodelsavedstate.delegatedproperty.SavedStateLiveDataSerializableProperty
import com.wada811.viewmodelsavedstate.delegatedproperty.SavedStateProperty
import com.wada811.viewmodelsavedstate.delegatedproperty.SavedStateSerializableProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

internal interface ISavedStateViewModel {
    val <T : ISavedStateViewModel> T.savedStateHandle: SavedStateHandle

    @Suppress("UNCHECKED_CAST")
    fun <T> ISavedStateViewModel.savedStateProperty(
        clazz: Class<T>,
        key: String? = null
    ): ReadWriteProperty<ISavedStateViewModel, T> {
        return if (clazz.isEnum) {
            savedStateProperty<Int?, T>({ it.toEnum(clazz) as T }, { it.toInt() }, key)
        } else {
            SavedStateProperty(savedStateHandle, key)
        }
    }

    fun <T, R> ISavedStateViewModel.savedStateProperty(
        deserialize: (T) -> R,
        serialize: (R) -> T,
        key: String? = null
    ): ReadWriteProperty<ISavedStateViewModel, R> {
        return SavedStateSerializableProperty(savedStateHandle, key, deserialize, serialize)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> ISavedStateViewModel.savedStateLiveData(
        clazz: Class<T>,
        key: String? = null
    ): ReadOnlyProperty<ISavedStateViewModel, MutableLiveData<T>> {
        return if (clazz.isEnum) {
            savedStateLiveData<Int?, T>({ it.toEnum(clazz) as T }, { it.toInt() }, key)
        } else {
            SavedStateLiveDataProperty(savedStateHandle, key)
        }
    }

    fun <T, R> ISavedStateViewModel.savedStateLiveData(
        deserialize: (T) -> R,
        serialize: (R) -> T,
        key: String? = null
    ): ReadOnlyProperty<ISavedStateViewModel, MutableLiveData<R>> {
        return SavedStateLiveDataSerializableProperty(savedStateHandle, key, deserialize, serialize)
    }
}


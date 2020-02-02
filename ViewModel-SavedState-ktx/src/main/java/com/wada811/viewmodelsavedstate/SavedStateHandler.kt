package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.extension.toEnum
import com.wada811.viewmodelsavedstate.extension.toInt
import com.wada811.viewmodelsavedstate.property.SavedStateLiveDataProperty
import com.wada811.viewmodelsavedstate.property.SavedStateLiveDataSerializableProperty
import com.wada811.viewmodelsavedstate.property.SavedStateProperty
import com.wada811.viewmodelsavedstate.property.SavedStateSerializableProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

internal interface SavedStateHandler {
    val <T : SavedStateHandler> T.savedStateHandle: SavedStateHandle

    @Suppress("UNCHECKED_CAST")
    fun <T> SavedStateHandle.property(
        clazz: Class<T>
    ): ReadWriteProperty<SavedStateHandler, T> {
        return if (clazz.isEnum) {
            property<Int?, T>({ it.toEnum(clazz) as T }, { it.toInt() })
        } else {
            SavedStateProperty(savedStateHandle)
        }
    }

    fun <T, R> SavedStateHandle.property(
        deserialize: (T) -> R,
        serialize: (R) -> T
    ): ReadWriteProperty<SavedStateHandler, R> {
        return SavedStateSerializableProperty(savedStateHandle, deserialize, serialize)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> SavedStateHandle.liveData(
        clazz: Class<T>
    ): ReadOnlyProperty<SavedStateHandler, MutableLiveData<T>> {
        return if (clazz.isEnum) {
            liveData<Int?, T>({ it.toEnum(clazz) as T }, { it.toInt() })
        } else {
            SavedStateLiveDataProperty(savedStateHandle)
        }
    }

    fun <T, R> SavedStateHandle.liveData(
        deserialize: (T) -> R,
        serialize: (R) -> T
    ): ReadOnlyProperty<SavedStateHandler, MutableLiveData<R>> {
        return SavedStateLiveDataSerializableProperty(savedStateHandle, deserialize, serialize)
    }
}


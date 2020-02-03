package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.extension.toEnum
import com.wada811.viewmodelsavedstate.extension.toInt
import com.wada811.viewmodelsavedstate.property.ReadOnlyPropertyProvider
import com.wada811.viewmodelsavedstate.property.ReadWritePropertyProvider
import com.wada811.viewmodelsavedstate.property.SavedStateLiveDataProperty
import com.wada811.viewmodelsavedstate.property.SavedStateLiveDataSerializableProperty
import com.wada811.viewmodelsavedstate.property.SavedStateProperty
import com.wada811.viewmodelsavedstate.property.SavedStateSerializableProperty
import com.wada811.viewmodelsavedstate.property.provideReadOnlyProperty
import com.wada811.viewmodelsavedstate.property.provideReadWriteProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

internal interface SavedStateHandler {
    @Suppress("UNCHECKED_CAST")
    @Deprecated("Use generic method.", ReplaceWith("this.property()"), DeprecationLevel.WARNING)
    fun <T> SavedStateHandle.property(clazz: Class<T>): ReadWriteProperty<SavedStateHandler, T> {
        return if (clazz.isEnum) {
            property<Int?, T>({ it.toEnum(clazz) as T }, { it.toInt() })
        } else {
            SavedStateProperty(this)
        }
    }

    @Suppress("DEPRECATION")
    @Deprecated("Use generic method.", ReplaceWith("this.property(initialValue)"), DeprecationLevel.WARNING)
    fun <S : SavedStateHandler, T> SavedStateHandle.property(clazz: Class<T>, initialValue: T): ReadWritePropertyProvider<S, T> {
        return provideReadWriteProperty {
            this[it] = initialValue
            property(clazz)
        }
    }

    fun <T, R> SavedStateHandle.property(deserialize: (T) -> R, serialize: (R) -> T): ReadWriteProperty<SavedStateHandler, R> {
        return SavedStateSerializableProperty(this, deserialize, serialize)
    }

    fun <T, R> SavedStateHandle.property(deserialize: (T) -> R, serialize: (R) -> T, initialValue: R): ReadWritePropertyProvider<SavedStateHandler, R> {
        return provideReadWriteProperty {
            this[it] = initialValue
            property(deserialize, serialize)
        }
    }

    @Suppress("UNCHECKED_CAST")
    @Deprecated("Use generic method.", ReplaceWith("this.liveData()"), DeprecationLevel.WARNING)
    fun <T> SavedStateHandle.liveData(clazz: Class<T>): ReadOnlyProperty<SavedStateHandler, MutableLiveData<T>> {
        return if (clazz.isEnum) {
            liveData<Int?, T>({ it.toEnum(clazz) as T }, { it.toInt() })
        } else {
            SavedStateLiveDataProperty(this)
        }
    }

    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    @Deprecated("Use generic method.", ReplaceWith("this.liveData(initialValue)"), DeprecationLevel.WARNING)
    fun <S : SavedStateHandler, T> SavedStateHandle.liveData(clazz: Class<T>, initialValue: T): ReadOnlyPropertyProvider<S, MutableLiveData<T>> {
        return provideReadOnlyProperty {
            this[it] = initialValue
            liveData(clazz)
        }
    }

    fun <T, R> SavedStateHandle.liveData(deserialize: (T) -> R, serialize: (R) -> T): ReadOnlyProperty<SavedStateHandler, MutableLiveData<R>> {
        return SavedStateLiveDataSerializableProperty(this, deserialize, serialize)
    }

    fun <S : SavedStateHandler, T, R> SavedStateHandle.liveData(deserialize: (T) -> R, serialize: (R) -> T, initialValue: R): ReadOnlyPropertyProvider<S, MutableLiveData<R>> {
        return provideReadOnlyProperty {
            this[it] = initialValue
            liveData(deserialize, serialize)
        }
    }
}


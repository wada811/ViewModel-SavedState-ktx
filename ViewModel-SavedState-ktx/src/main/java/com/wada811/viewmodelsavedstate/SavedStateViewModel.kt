package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("unused")
abstract class SavedStateViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    @Suppress("DEPRECATION")
    protected inline fun <reified T> savedStateProperty(key: String? = null): ReadWriteProperty<SavedStateViewModel, T> {
        return if (T::class.java.isEnum) _savedStateEnumProperty(T::class.java, key) else _savedStateBundleProperty(key)
    }

    @Suppress("FunctionName")
    @Deprecated("Use savedStateProperty<T>(key)", ReplaceWith("savedStateProperty(key)"), DeprecationLevel.WARNING)
    protected fun <T> _savedStateBundleProperty(key: String? = null): ReadWriteProperty<SavedStateViewModel, T> {
        return savedStateProperty<T, T>({ it }, { it }, key)
    }

    @Suppress("FunctionName", "UNCHECKED_CAST")
    @Deprecated("Use savedStateProperty<T>(key)", ReplaceWith("savedStateProperty(key)"), DeprecationLevel.WARNING)
    protected fun <T> _savedStateEnumProperty(enumClass: Class<T>, key: String?): ReadWriteProperty<SavedStateViewModel, T> {
        return savedStateProperty<Int?, T>({ it.toEnum(enumClass) as T }, { it.toInt() }, key)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun <T, R> savedStateProperty(deserialize: (T) -> R, serialize: (R) -> T, key: String? = null): ReadWriteProperty<SavedStateViewModel, R> {
        return SavedStateProperty(savedStateHandle, key, deserialize, serialize)
    }

    @Suppress("DEPRECATION")
    protected inline fun <reified T> savedStateLiveData(key: String? = null): ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {
        return if (T::class.java.isEnum) _savedStateEnumLiveData(T::class.java, key) else _savedStateBundleLiveData(key)
    }

    @Suppress("FunctionName")
    @Deprecated("Use savedStateLiveData<T>(key)", ReplaceWith("savedStateLiveData(key)"), DeprecationLevel.WARNING)
    protected fun <T> _savedStateBundleLiveData(key: String? = null): ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {
        return SavedStateLiveData(savedStateHandle, key)
    }

    @Suppress("FunctionName", "UNCHECKED_CAST")
    @Deprecated("Use savedStateLiveData<T>(key)", ReplaceWith("savedStateLiveData(key)"), DeprecationLevel.WARNING)
    protected fun <T> _savedStateEnumLiveData(enumClass: Class<T>, key: String?): ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {
        return savedStateLiveData<Int?, T>({ it.toEnum(enumClass) as T }, { it.toInt() }, key)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun <T, R> savedStateLiveData(deserialize: (T) -> R, serialize: (R) -> T, key: String? = null): ReadOnlyProperty<SavedStateViewModel, MutableLiveData<R>> {
        return SavedStateDelegatedLiveData(savedStateHandle, key, deserialize, serialize)
    }

    private class SavedStateProperty<T, R>(
        private val savedStateHandle: SavedStateHandle,
        private val key: String?,
        private val deserialize: (T) -> R,
        private val serialize: (R) -> T
    ) : ReadWriteProperty<SavedStateViewModel, R> {

        @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
        override fun getValue(thisRef: SavedStateViewModel, property: KProperty<*>): R {
            return deserialize(savedStateHandle.get<T>(key ?: property.name) as T)
        }

        override fun setValue(thisRef: SavedStateViewModel, property: KProperty<*>, value: R) {
            savedStateHandle.set(key ?: property.name, serialize(value))
        }
    }

    private class SavedStateLiveData<T>(
        private val savedStateHandle: SavedStateHandle,
        private val key: String?
    ) : ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {
        override fun getValue(thisRef: SavedStateViewModel, property: KProperty<*>): MutableLiveData<T> {
            return savedStateHandle.getLiveData(key ?: property.name)
        }
    }

    private class SavedStateDelegatedLiveData<T, R>(
        private val savedStateHandle: SavedStateHandle,
        private val key: String?,
        private val deserialize: (T) -> R,
        private val serialize: (R) -> T
    ) : ReadOnlyProperty<SavedStateViewModel, MutableLiveData<R>> {
        private var savedStateLiveData: MutableLiveData<R>? = null

        override fun getValue(thisRef: SavedStateViewModel, property: KProperty<*>): MutableLiveData<R> {
            if (savedStateLiveData == null) {
                savedStateLiveData = SavedStateLiveData(savedStateHandle, key ?: property.name, deserialize, serialize)
            }
            return savedStateLiveData!!
        }

        @Suppress("UNCHECKED_CAST")
        private class SavedStateLiveData<T, R>(
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
}

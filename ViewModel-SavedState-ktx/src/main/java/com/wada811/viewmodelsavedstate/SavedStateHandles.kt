package com.wada811.viewmodelsavedstate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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

fun <T> SavedStateHandle.property(): ReadWriteProperty<ViewModel, T> {
    return SavedStateProperty(this)
}

fun <T> SavedStateHandle.property(initialValue: T): ReadWritePropertyProvider<ViewModel, T> {
    return provideReadWriteProperty {
        this[it] = initialValue
        property<T>()
    }
}

fun <TValue, TState> SavedStateHandle.property(adapter: SavedStateAdapter<TValue, TState>): ReadWriteProperty<ViewModel, TValue> {
    return SavedStateSerializableProperty(this, adapter)
}

fun <TValue, TState> SavedStateHandle.property(adapter: SavedStateAdapter<TValue, TState>, initialValue: TValue): ReadWritePropertyProvider<ViewModel, TValue> {
    return provideReadWriteProperty {
        this[it] = initialValue
        property(adapter)
    }
}

fun <T> SavedStateHandle.liveData(): ReadOnlyProperty<ViewModel, MutableLiveData<T>> {
    return SavedStateLiveDataProperty(this)
}

fun <T> SavedStateHandle.liveData(initialValue: T): ReadOnlyPropertyProvider<ViewModel, MutableLiveData<T>> {
    return provideReadOnlyProperty {
        this[it] = initialValue
        liveData<T>()
    }
}

fun <TValue, TState> SavedStateHandle.liveData(adapter: SavedStateAdapter<TValue, TState>): ReadOnlyProperty<ViewModel, MutableLiveData<TValue>> {
    return SavedStateLiveDataSerializableProperty(this, adapter)
}

fun <TValue, TState> SavedStateHandle.liveData(adapter: SavedStateAdapter<TValue, TState>, initialValue: TValue): ReadOnlyPropertyProvider<ViewModel, MutableLiveData<TValue>> {
    return provideReadOnlyProperty {
        this[it] = initialValue
        liveData(adapter)
    }
}


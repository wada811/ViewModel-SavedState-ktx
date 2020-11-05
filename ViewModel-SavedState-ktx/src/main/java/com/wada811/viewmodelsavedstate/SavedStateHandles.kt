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

fun <T> SavedStateHandle.property(): ReadWriteProperty<ViewModel, T> = SavedStateProperty(this)
fun <T> SavedStateHandle.property(defaultValue: T): ReadWritePropertyProvider<ViewModel, T> {
    return provideReadWriteProperty {
        if (!this.contains(it)) {
            this[it] = defaultValue
        }
        property()
    }
}

fun <TValue, TState> SavedStateHandle.property(adapter: SavedStateAdapter<TValue, TState>): ReadWriteProperty<ViewModel, TValue> = SavedStateSerializableProperty(this, adapter)
fun <TValue, TState> SavedStateHandle.property(adapter: SavedStateAdapter<TValue, TState>, defaultValue: TValue): ReadWritePropertyProvider<ViewModel, TValue> {
    return provideReadWriteProperty {
        if (!this.contains(it)) {
            this[it] = adapter.toSavedState(defaultValue)
        }
        property(adapter)
    }
}

fun <T> SavedStateHandle.liveData(): ReadOnlyProperty<ViewModel, MutableLiveData<T>> = SavedStateLiveDataProperty(this)
fun <T> SavedStateHandle.liveData(defaultValue: T): ReadOnlyPropertyProvider<ViewModel, MutableLiveData<T>> {
    return provideReadOnlyProperty {
        if (!this.contains(it)) {
            this[it] = defaultValue
        }
        liveData()
    }
}

fun <TValue, TState> SavedStateHandle.liveData(adapter: SavedStateAdapter<TValue, TState>): ReadOnlyProperty<ViewModel, MutableLiveData<TValue>> = SavedStateLiveDataSerializableProperty(this, adapter)
fun <TValue, TState> SavedStateHandle.liveData(adapter: SavedStateAdapter<TValue, TState>, defaultValue: TValue): ReadOnlyPropertyProvider<ViewModel, MutableLiveData<TValue>> {
    return provideReadOnlyProperty {
        if (!this.contains(it)) {
            this[it] = adapter.toSavedState(defaultValue)
        }
        liveData(adapter)
    }
}


package com.wada811.viewmodelsavedstatektx

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wada811.viewmodelsavedstatektx.property.SavedStateLiveDataProperty
import com.wada811.viewmodelsavedstatektx.property.SavedStateLiveDataSerializableProperty
import com.wada811.viewmodelsavedstatektx.property.SavedStateProperty
import com.wada811.viewmodelsavedstatektx.property.SavedStateSerializableProperty
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> SavedStateHandle.property(): ReadWriteProperty<ViewModel, T> = SavedStateProperty(this)
fun <T> SavedStateHandle.property(defaultValue: T): PropertyDelegateProvider<ViewModel, ReadWriteProperty<ViewModel, T>> {
    return PropertyDelegateProvider { _: ViewModel, property: KProperty<*> ->
        if (!this.contains(property.name)) {
            this[property.name] = defaultValue
        }
        property()
    }
}

fun <TValue, TState> SavedStateHandle.property(adapter: SavedStateAdapter<TValue, TState>): ReadWriteProperty<ViewModel, TValue> = SavedStateSerializableProperty(this, adapter)
fun <TValue, TState> SavedStateHandle.property(adapter: SavedStateAdapter<TValue, TState>, defaultValue: TValue): PropertyDelegateProvider<ViewModel, ReadWriteProperty<ViewModel, TValue>> {
    return PropertyDelegateProvider { _: ViewModel, property: KProperty<*> ->
        if (!this.contains(property.name)) {
            this[property.name] = adapter.toSavedState(defaultValue)
        }
        property(adapter)
    }
}

fun <T> SavedStateHandle.liveData(): ReadOnlyProperty<ViewModel, MutableLiveData<T>> = SavedStateLiveDataProperty(this)
fun <T> SavedStateHandle.liveData(defaultValue: T): PropertyDelegateProvider<ViewModel, ReadOnlyProperty<ViewModel, MutableLiveData<T>>> {
    return PropertyDelegateProvider { _: ViewModel, property: KProperty<*> ->
        if (!this.contains(property.name)) {
            this[property.name] = defaultValue
        }
        liveData()
    }
}

fun <TValue, TState> SavedStateHandle.liveData(adapter: SavedStateAdapter<TValue, TState>): ReadOnlyProperty<ViewModel, MutableLiveData<TValue>> = SavedStateLiveDataSerializableProperty(this, adapter)
fun <TValue, TState> SavedStateHandle.liveData(adapter: SavedStateAdapter<TValue, TState>, defaultValue: TValue): PropertyDelegateProvider<ViewModel, ReadOnlyProperty<ViewModel, MutableLiveData<TValue>>> {
    return PropertyDelegateProvider { _: ViewModel, property: KProperty<*> ->
        if (!this.contains(property.name)) {
            this[property.name] = adapter.toSavedState(defaultValue)
        }
        liveData(adapter)
    }
}

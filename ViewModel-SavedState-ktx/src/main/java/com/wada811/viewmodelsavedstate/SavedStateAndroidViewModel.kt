package com.wada811.viewmodelsavedstate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.property.ReadOnlyPropertyProvider
import com.wada811.viewmodelsavedstate.property.ReadWritePropertyProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

abstract class SavedStateAndroidViewModel(
    @get:JvmName("application")
    protected val application: Application,
    @Suppress("unused") private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application), SavedStateHandler {
    @Suppress("DEPRECATION")
    protected inline fun <reified T> SavedStateHandle.property(): ReadWriteProperty<SavedStateAndroidViewModel, T> {
        return property(T::class.java)
    }

    @Suppress("DEPRECATION")
    protected inline fun <reified T> SavedStateHandle.property(initialValue: T): ReadWritePropertyProvider<SavedStateAndroidViewModel, T> {
        return property(T::class.java, initialValue)
    }

    @Suppress("DEPRECATION")
    protected inline fun <reified T> SavedStateHandle.liveData(): ReadOnlyProperty<SavedStateAndroidViewModel, MutableLiveData<T>> {
        return liveData(T::class.java)
    }

    @Suppress("DEPRECATION")
    protected inline fun <reified T> SavedStateHandle.liveData(initialValue: T): ReadOnlyPropertyProvider<SavedStateAndroidViewModel, MutableLiveData<T>> {
        return liveData(T::class.java, initialValue)
    }
}
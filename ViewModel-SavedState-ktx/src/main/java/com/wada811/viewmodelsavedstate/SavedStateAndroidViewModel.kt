package com.wada811.viewmodelsavedstate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

abstract class SavedStateAndroidViewModel(
    @get:JvmName("application")
    protected val application: Application,
    private val _savedStateHandle: SavedStateHandle
) : AndroidViewModel(application), SavedStateHandler {
    override val <T : SavedStateHandler> T.savedStateHandle: SavedStateHandle
        get() = _savedStateHandle

    protected inline fun <reified T> SavedStateHandle.property(): ReadWriteProperty<SavedStateAndroidViewModel, T> = property(T::class.java)
    protected inline fun <reified T> SavedStateHandle.liveData(): ReadOnlyProperty<SavedStateAndroidViewModel, MutableLiveData<T>> = liveData(T::class.java)
}
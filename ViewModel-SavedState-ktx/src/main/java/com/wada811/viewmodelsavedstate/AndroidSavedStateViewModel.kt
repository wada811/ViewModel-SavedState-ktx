package com.wada811.viewmodelsavedstate

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

@Suppress("unused")
abstract class AndroidSavedStateViewModel(
    @get:JvmName("application")
    protected val application: Application,
    private val _savedStateHandle: SavedStateHandle
) : AndroidViewModel(application), ISavedStateViewModel {
    override val <T : ISavedStateViewModel> T.savedStateHandle: SavedStateHandle
        get() = _savedStateHandle

    protected inline fun <reified T> savedStateProperty(key: String? = null): ReadWriteProperty<AndroidSavedStateViewModel, T> {
        return savedStateProperty(T::class.java, key)
    }

    protected inline fun <reified T> savedStateLiveData(key: String? = null): ReadOnlyProperty<AndroidSavedStateViewModel, MutableLiveData<T>> {
        return savedStateLiveData(T::class.java, key)
    }
}
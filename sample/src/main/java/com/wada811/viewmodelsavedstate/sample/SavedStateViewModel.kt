package com.wada811.viewmodelsavedstate.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.SavedStateViewModel

class Sample {
class SampleViewModel(
    savedStateHandle: SavedStateHandle
) : SavedStateViewModel(savedStateHandle) {
    companion object {
        private const val KEY_TEXT = "text"
    }

    var text: String by savedStateProperty(KEY_TEXT)
    val textLiveData: LiveData<String> by savedStateLiveData(KEY_TEXT)
}
}
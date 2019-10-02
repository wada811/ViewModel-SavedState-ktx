package com.wada811.viewmodelsavedstate.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.SavedStateViewModel

class SampleViewModel(savedStateHandle: SavedStateHandle) : SavedStateViewModel(savedStateHandle) {
    private var viewModelCount = MutableLiveData(0)
    var viewModelCountText: LiveData<String> = MediatorLiveData<String>().also { liveData ->
        liveData.addSource(viewModelCount) { count ->
            liveData.value = "$count"
        }
    }

    val savedStateCount: MutableLiveData<Int> by savedStateLiveData(SampleViewModel::savedStateCount.name)
    var savedStateCountText: LiveData<String> = MediatorLiveData<String>().also { liveData ->
        liveData.addSource(savedStateCount) { count ->
            liveData.value = "$count"
        }
    }

    val log: MutableLiveData<String> by savedStateLiveData(SampleViewModel::log.name)

    init {
        appendLog("ViewModel::init")
    }

    fun countUp() {
        viewModelCount.value = viewModelCount.value?.plus(1)
        savedStateCount.value = savedStateCount.value?.plus(1)
    }

    fun appendLog(text: String) {
        val maxLineCount = 5
        val logLines = log.value!!.split("\n")
        if (logLines.size > maxLineCount) {
            log.value = logLines.subList(logLines.size - maxLineCount + 1, logLines.size).joinToString("\n")
        }
        log.value = log.value + "\n$text"
    }

    override fun onCleared() {
        super.onCleared()
        appendLog("ViewModel::onCleared")
    }
}
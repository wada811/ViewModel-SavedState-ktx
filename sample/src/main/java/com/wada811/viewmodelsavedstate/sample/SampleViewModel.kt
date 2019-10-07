package com.wada811.viewmodelsavedstate.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wada811.viewmodelsavedstate.SavedStateViewModel

class SampleViewModel(savedStateHandle: SavedStateHandle) : SavedStateViewModel(savedStateHandle) {
    enum class CountUpValue(val count: Int) {
        ONE(1),
        TEN(10)
    }

    private var viewModelCount = MutableLiveData(0)
    var viewModelCountText: LiveData<String> = MediatorLiveData<String>().also { liveData ->
        liveData.addSource(viewModelCount) { count ->
            liveData.value = "$count"
        }
    }
    val countUpValueIntLiveData: MutableLiveData<Int?> by savedStateLiveData(this::countUpValueEnumLiveData.name)
    val countUpValueEnumLiveData: MutableLiveData<CountUpValue?> by savedStateLiveData()
    val savedStateCount: MutableLiveData<Int> by savedStateLiveData()
    var savedStateCountText: LiveData<String> = MediatorLiveData<String>().also { liveData ->
        liveData.addSource(savedStateCount) { count ->
            liveData.value = "$count"
        }
    }

    val log: MutableLiveData<String> by savedStateLiveData()

    init {
        appendLog("ViewModel::init")
    }

    fun countUp() {
        viewModelCount.value = viewModelCount.value?.plus(CountUpValue.values().firstOrNull { it.ordinal == countUpValueIntLiveData.value }?.count ?: 0)
        savedStateCount.value = savedStateCount.value?.plus(countUpValueEnumLiveData.value?.count ?: 0)
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
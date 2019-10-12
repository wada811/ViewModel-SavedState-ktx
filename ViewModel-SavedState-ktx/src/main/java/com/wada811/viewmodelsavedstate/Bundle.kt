package com.wada811.viewmodelsavedstate

import android.os.Bundle

@Suppress("unused")
fun <T : Enum<T>> Bundle.putEnum(key: String, enum: T) = this.putInt(key, enum.toInt())

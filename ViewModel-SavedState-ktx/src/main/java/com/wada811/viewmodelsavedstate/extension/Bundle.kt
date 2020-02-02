package com.wada811.viewmodelsavedstate.extension

import android.os.Bundle

fun <T : Enum<T>> Bundle.putEnum(key: String, enum: T) = this.putInt(key, enum.toInt())

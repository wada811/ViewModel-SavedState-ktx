package com.wada811.viewmodelsavedstate.extension

import android.content.Intent

fun <T : Enum<T>> Intent.putEnumExtra(key: String, enum: T) = this.putExtra(key, enum.toInt())

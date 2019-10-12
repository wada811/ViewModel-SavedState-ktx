package com.wada811.viewmodelsavedstate

import android.content.Intent

@Suppress("unused")
fun <T : Enum<T>> Intent.putEnumExtra(key: String, enum: T) = this.putExtra(key, enum.toInt())

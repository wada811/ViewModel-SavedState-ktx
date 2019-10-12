package com.wada811.viewmodelsavedstate

internal fun <T> Int?.toEnum(enumClass: Class<T>): T? = this?.let { enumClass.enumConstants!![this] }
internal fun <T> T?.toInt(): Int? = (this as? Enum<*>)?.ordinal
internal fun <T : Enum<T>> T.toInt(): Int = this.ordinal
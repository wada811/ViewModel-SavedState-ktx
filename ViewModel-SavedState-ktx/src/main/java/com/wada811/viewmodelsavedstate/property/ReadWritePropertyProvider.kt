package com.wada811.viewmodelsavedstate.property

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class ReadWritePropertyProvider<T, R>(private val provide: (String) -> ReadWriteProperty<T, R>) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadWriteProperty<T, R> = provide(property.name)
}

internal fun <T, R> provideReadWriteProperty(provide: (String) -> ReadWriteProperty<T, R>): ReadWritePropertyProvider<T, R> {
    return object : ReadWritePropertyProvider<T, R>(provide) {}
}
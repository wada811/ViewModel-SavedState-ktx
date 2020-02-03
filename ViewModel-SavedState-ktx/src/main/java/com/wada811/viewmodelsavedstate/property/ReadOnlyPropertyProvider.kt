package com.wada811.viewmodelsavedstate.property

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class ReadOnlyPropertyProvider<T, R>(private val provide: (String) -> ReadOnlyProperty<T, R>) {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): ReadOnlyProperty<T, R> = provide(property.name)
}

internal fun <T, R> provideReadOnlyProperty(provide: (String) -> ReadOnlyProperty<T, R>): ReadOnlyPropertyProvider<T, R> {
    return object : ReadOnlyPropertyProvider<T, R>(provide) {}
}
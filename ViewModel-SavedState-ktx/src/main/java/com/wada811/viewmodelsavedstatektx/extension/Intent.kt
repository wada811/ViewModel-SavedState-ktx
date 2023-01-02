@file:Suppress("unused")

package com.wada811.viewmodelsavedstatektx.extension

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import kotlin.reflect.KProperty

fun Intent.putExtra(property: KProperty<*>, value: Boolean) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: BooleanArray) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Double) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: DoubleArray) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Int) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: IntArray) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Long) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: LongArray) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: String) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Array<String?>) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Array<CharSequence?>) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Array<Parcelable?>) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Bundle) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Byte) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: ByteArray) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Char) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: CharArray) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: CharSequence) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Float) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: FloatArray) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Parcelable) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Serializable) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: Short) = this.putExtra(property.name, value)
fun Intent.putExtra(property: KProperty<*>, value: ShortArray) = this.putExtra(property.name, value)
fun Intent.putIntegerArrayListExtra(property: KProperty<*>, value: ArrayList<Int?>) = this.putIntegerArrayListExtra(property.name, value)
fun Intent.putStringArrayListExtra(property: KProperty<*>, value: ArrayList<String?>) = this.putStringArrayListExtra(property.name, value)
fun Intent.putCharSequenceArrayListExtra(property: KProperty<*>, value: ArrayList<CharSequence?>) = this.putCharSequenceArrayListExtra(property.name, value)
fun Intent.putParcelableArrayListExtra(property: KProperty<*>, value: ArrayList<Parcelable?>) = this.putParcelableArrayListExtra(property.name, value)

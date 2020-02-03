package com.wada811.viewmodelsavedstate.extension

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable
import kotlin.reflect.KProperty

fun <T : Enum<T>> Bundle.putEnum(property: KProperty<*>, enum: T) = this.putInt(property.name, enum.toInt())
fun Bundle.putBoolean(property: KProperty<*>, value: Boolean) = this.putBoolean(property.name, value)
fun Bundle.putBooleanArray(property: KProperty<*>, value: BooleanArray) = this.putBooleanArray(property.name, value)
fun Bundle.putDouble(property: KProperty<*>, value: Double) = this.putDouble(property.name, value)
fun Bundle.putDoubleArray(property: KProperty<*>, value: DoubleArray) = this.putDoubleArray(property.name, value)
fun Bundle.putInt(property: KProperty<*>, value: Int) = this.putInt(property.name, value)
fun Bundle.putIntArray(property: KProperty<*>, value: IntArray) = this.putIntArray(property.name, value)
fun Bundle.putLong(property: KProperty<*>, value: Long) = this.putLong(property.name, value)
fun Bundle.putLongArray(property: KProperty<*>, value: LongArray) = this.putLongArray(property.name, value)
fun Bundle.putString(property: KProperty<*>, value: String) = this.putString(property.name, value)
fun Bundle.putStringArray(property: KProperty<*>, value: Array<String?>) = this.putStringArray(property.name, value)
fun Bundle.putCharSequenceArray(property: KProperty<*>, value: Array<CharSequence?>) = this.putCharSequenceArray(property.name, value)
fun Bundle.putParcelableArray(property: KProperty<*>, value: Array<Parcelable?>) = this.putParcelableArray(property.name, value)
fun Bundle.putBundle(property: KProperty<*>, value: Bundle) = this.putBundle(property.name, value)
fun Bundle.putByte(property: KProperty<*>, value: Byte) = this.putByte(property.name, value)
fun Bundle.putByteArray(property: KProperty<*>, value: ByteArray) = this.putByteArray(property.name, value)
fun Bundle.putChar(property: KProperty<*>, value: Char) = this.putChar(property.name, value)
fun Bundle.putCharArray(property: KProperty<*>, value: CharArray) = this.putCharArray(property.name, value)
fun Bundle.putCharSequence(property: KProperty<*>, value: CharSequence) = this.putCharSequence(property.name, value)
fun Bundle.putFloat(property: KProperty<*>, value: Float) = this.putFloat(property.name, value)
fun Bundle.putFloatArray(property: KProperty<*>, value: FloatArray) = this.putFloatArray(property.name, value)
fun Bundle.putParcelable(property: KProperty<*>, value: Parcelable) = this.putParcelable(property.name, value)
fun Bundle.putSerializable(property: KProperty<*>, value: Serializable) = this.putSerializable(property.name, value)
fun Bundle.putShort(property: KProperty<*>, value: Short) = this.putShort(property.name, value)
fun Bundle.putShortArray(property: KProperty<*>, value: ShortArray) = this.putShortArray(property.name, value)
fun Bundle.putIntegerArrayList(property: KProperty<*>, value: ArrayList<Int?>) = this.putIntegerArrayList(property.name, value)
fun Bundle.putStringArrayList(property: KProperty<*>, value: ArrayList<String?>) = this.putStringArrayList(property.name, value)
fun Bundle.putCharSequenceArrayList(property: KProperty<*>, value: ArrayList<CharSequence?>) = this.putCharSequenceArrayList(property.name, value)
fun Bundle.putParcelableArrayList(property: KProperty<*>, value: ArrayList<Parcelable?>) = this.putParcelableArrayList(property.name, value)

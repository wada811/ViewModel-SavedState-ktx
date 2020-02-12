ViewModel-SavedState-ktx
=====

`ViewModel-SavedState-ktx` make easy handling saved state by delegated property.

## Overview

`ViewModel-SavedState-ktx` is kotlin extensions of [Saved State module for ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate).

### What is ViewModel-SavedState
Up to now, UI states is usually stored in `onSavedInstanceState` and restored in `onCreate`.
From now on, you can store and restore UI states by SavedStateHandle using ViewModel-SavedState.

### Why use ViewModel-SavedState
ViewModel has been kept alive when configuration changes occurred, but ViewModel has been destroyed when Activity killed by OS.
By using ViewModel-SavedState, ViewModel save its property when Activity killed by OS.

### How to use ViewModel-SavedState

#### How to get SavedStateHandle
- You can get a SavedStateHandle instance via ViewModel's constructor.

#### How to pass SavedStateHandle to ViewModel constructor
- If you call `by viewModels()` in Activity or Fragment, `SavedStateHandle` is automatically passed.
    - The `intent.extra` or `arguments` is passed automatically to `SavedStateHandle`.
- If you want to pass parameters other than `SavedStateHandle` or `Application, SavedStateHandle` into ViewModel's constructor, you pass a ViewModel's factory into `by viewModels`.
    - The ViewModel's factory needs to extend `AbstractSavedStateViewModelFactory`.
    - The `intent.extra` or `arguments` needs to pass manually to the ViewModel's factory if need it.

#### How to use SavedStateHandle
- You can use a value by `SavedStateHandle#get(key)` and `SavedStateHandle#set(key, value)`
    - The value is initialized by value of same key in `intent.extra` or `arguments`.
- You can use a LiveData instance by `SavedStateHandle#getLiveData(key)`
    - If you change the LiveData instance's value, a value of SavedStateHandle is changed.

### Problems in ViewModel-SavedState
- If you get a value by `SavedStateHandle#get(key)`, you don't only change the value but also need to call `SavedStateHandle#set(key, value)`.
- `SavedStateHandle` requires a key to use.
    - The key needs to be same as a key of `intent.extra` or `arguments`.
- `SavedStateHandle` has restrictions on available types.

### Solutions by ViewModel-SavedState-ktx
- When you change the value, `SavedStateHandle#set(key, value)` is automatically called by Delegated Properties.
- ViewModel-SavedState-ktx don't requires a key to use `SavedStateHandle`. Instead, it uses property name.
    - It provides extension methods for specifying property name in `Intent` and `Bundle`.
- You can use any type using `SavedStateAdapter` that converts between the type used in `SavedStateHandle` and the type actually used in `ViewModel`.

## Usage

You can pass parameters of `Intent` or `Bundle` to ViewModel property by extension methods using its property reference.

```kotlin
class SampleActivity : AppCompatActivity(R.layout.sample_activity) {
    private val viewModel: SampleViewModel by viewModels()

    companion object {
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, SampleActivity::class.java).also {
            it.putExtra(SampleViewModel::text, "sample")
        }
    }
}
```

You can get a value or a LiveData instance by delegated property using SavedStateHandle's extension methods.

```kotlin
class SampleViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val text: String by savedStateHandle.property()
    val count: MutableLiveData<Int> by savedStateHandle.liveData(initialValue = 0)
    val timeUnit: MutableLiveData<TimeUnit?> by savedStateHandle.liveData(object : SavedStateAdapter<TimeUnit?, Int?> {
        override fun toSavedState(value: TimeUnit?): Int? = value?.ordinal
        override fun fromSavedState(state: Int?): TimeUnit? = state?.let { TimeUnit.values()[it] }
    })
}
```

## Gradle

[![](https://jitpack.io/v/wada811/ViewModel-SavedState-ktx.svg)](https://jitpack.io/#wada811/ViewModel-SavedState-ktx)

```groovy
repositories {
    maven { url "https://www.jitpack.io" }
}

dependencies {
    implementation 'com.github.wada811:ViewModel-SavedState-ktx:x.y.z'
}
```

## License

Copyright (C) 2019 wada811

Licensed under the Apache License, Version 2.0

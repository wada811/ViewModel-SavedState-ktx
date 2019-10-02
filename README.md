ViewModel-SavedState-ktx
=====

`ViewModel-SavedState-ktx` make easy handling saved state by delegated property.

## Usage

```kotlin
class SessionViewModel(savedStateHandle: SavedStateHandle) : SavedStateViewModel(savedStateHandle) {
    val sessionId: String by savedStateProperty("key of sessionId")
    val isLiked: MutableLiveData<Boolean> by savedStateLiveData("key of isLiked")
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

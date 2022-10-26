# rawg.io
Unofficial mobile application of rawg.io database using Exoplayer✨
# This app uses:
- API: [rawg](https://rawg.io/apidocs)

## Libraries
- ExoPlayer
```
implementation "com.google.android.exoplayer:exoplayer:2.16.1"
```
- Retrofit
```
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"
implementation "com.squareup.okhttp3:logging-interceptor:4.9.3"
implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'
```
- Kotlin Coroutines
```
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"
```
- Hilt
```
implementation 'com.google.dagger:hilt-android:2.42'
kapt 'com.google.dagger:hilt-compiler:2.42'
```
- Glide
```
implementation 'com.github.bumptech.glide:glide:4.13.1'
kapt 'com.github.bumptech.glide:compiler:4.13.1'
```
- Navigation
```
implementation 'androidx.navigation:navigation-fragment-ktx:2.5.2'
implementation 'androidx.navigation:navigation-ui-ktx:2.5.2'
```
- MVVM
- Clean Architecture



Other Libraries:
```
// Image Slider
implementation 'com.github.denzcoskun:ImageSlideshow:0.1.0'

// Chucker
debugImplementation "com.github.chuckerteam.chucker:library:3.5.2"
releaseImplementation "com.github.chuckerteam.chucker:library-no-op:3.4.0"

// Photo view
implementation 'com.github.chrisbanes:PhotoView:2.3.0'

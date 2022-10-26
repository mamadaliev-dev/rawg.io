# rawg.io
Unofficial mobile app of [rawg](https://rawg.io/apidocs) database using Exoplayer✨
![photo_2022-10-27_00-19-20](https://user-images.githubusercontent.com/100607586/198117323-cef8ed8a-40c8-44cf-9277-0945c5fcb2df.jpg)
![photo_2022-10-27_00-19-30](https://user-images.githubusercontent.com/100607586/198117333-a456a24f-2fc8-4056-96c1-e65a7e7bce2b.jpg)
![photo_2022-10-27_00-19-33](https://user-images.githubusercontent.com/100607586/198117359-50240a7f-1149-4c38-b47d-147b4a6b7c85.jpg)
![photo_2022-10-27_00-19-37](https://user-images.githubusercontent.com/100607586/198117363-1ba35261-4fd2-41b4-89ce-b4f627958a17.jpg)
![photo_2022-10-27_00-19-40](https://user-images.githubusercontent.com/100607586/198117377-a0e862a4-3889-45be-9c5a-d719f9d41569.jpg)
![photo_2022-10-27_00-19-44](https://user-images.githubusercontent.com/100607586/198117381-18cd2e08-2b1f-4c27-b760-d6090640e775.jpg)

### Libraries💻
ExoPlayer:
```
implementation "com.google.android.exoplayer:exoplayer:2.16.1"
```
Retrofit:
```
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"
implementation "com.squareup.okhttp3:logging-interceptor:4.9.3"
implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'
```
Kotlin Coroutines:
```
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"
```
Hilt:
```
implementation 'com.google.dagger:hilt-android:2.42'
kapt 'com.google.dagger:hilt-compiler:2.42'
```
Glide:
```
implementation 'com.github.bumptech.glide:glide:4.13.1'
kapt 'com.github.bumptech.glide:compiler:4.13.1'
```
Navigation:
```
implementation 'androidx.navigation:navigation-fragment-ktx:2.5.2'
implementation 'androidx.navigation:navigation-ui-ktx:2.5.2'
```

### Other Libraries🧾️
```
// Image Slider
implementation 'com.github.denzcoskun:ImageSlideshow:0.1.0'

// Chucker
debugImplementation "com.github.chuckerteam.chucker:library:3.5.2"
releaseImplementation "com.github.chuckerteam.chucker:library-no-op:3.4.0"

// Photo view
implementation 'com.github.chrisbanes:PhotoView:2.3.0'

# MovieDB Explorer

Another Movie app

## Tech Stack

### Architecture

MVVM + Repository pattern

(Diagram & Explanation here...)

### Libraries

- [x] [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [x] [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [x] [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)
- [x] [Retrofit](https://square.github.io/retrofit/)
- [x] [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [x] [Koin](https://start.insert-koin.io/#/quickstart/kotlin)
- [x] [Coil](https://github.com/coil-kt/coil)
- [x] [Chucker](https://github.com/ChuckerTeam/chucker)
- [x] [Jetpack Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
- [x] [Room](https://developer.android.com/jetpack/androidx/releases/room)


## API

[The Movie DB](https://www.themoviedb.org/documentation/api)

Register [here](https://developers.themoviedb.org/3/getting-started/introduction) to obtain api key

Then, put the key inside `local.properties`
```
# default local.properties content
# ...

API_KEY="put the key here"

```
<p align="center">
<img src="https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593115086/MovieDB%20Explorer/ic_launcher.png" width="72" alt="Logo">
</p>
<h1 align="center">MovieDB Explorer</h1>

<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/hyuwah"><img alt="Profile" src="https://badgen.net/badge/icon/hyuwah?icon=github&label"/></a> 
</p>

Another Movie app (part of technical test submission)

## Preview

### Screenshot

<p align="center">
  <img src="https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593113639/MovieDB%20Explorer/home.png" width="270" alt="Home">
  <img src="https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593113640/MovieDB%20Explorer/detail.png" width="270" alt="Detail">
  <img src="https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593113639/MovieDB%20Explorer/favorites.png" width="270" alt="Favorites">
</p>

### Demo

#### Basic Flow

![](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593141223/MovieDB%20Explorer/basc_flow_optimized.gif)

#### Review (No Internet)

![](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593113657/MovieDB%20Explorer/no_internet_review.gif)

#### Home (No Internet)

![](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593113651/MovieDB%20Explorer/no_internet_home.gif)

#### Empty Favorite

![](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593113643/MovieDB%20Explorer/empty_favorites.gif)

#### Share Movie

![](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1593113656/MovieDB%20Explorer/share_movie.gif)

## Tech Stack

### Architecture

MVVM + Repository pattern

(Diagram & Explanation here...)

### Libraries

#### Core & Jetpack

- [x] [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [x] [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [x] [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)
- [x] [Retrofit](https://square.github.io/retrofit/): Networking
- [x] [View Binding](https://developer.android.com/topic/libraries/view-binding)
- [x] [Koin](https://start.insert-koin.io/#/quickstart/kotlin): Dependency Injection / Service Locator
- [x] [Coil](https://github.com/coil-kt/coil): Image
- [x] [Jetpack Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
- [x] [Room](https://developer.android.com/jetpack/androidx/releases/room): Database

#### UI & View

- [x] [Markwon](https://github.com/noties/Markwon): Markdown parser
- [x] [ShowMoreText](https://github.com/mahimrocky/ShowMoreText)

#### Development, Debug & Test

- [x] [Chucker](https://github.com/ChuckerTeam/chucker): Network inspection
- [x] [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)
- [x] [MockK](https://mockk.io/)


## API

[The Movie DB](https://www.themoviedb.org/documentation/api)

Register [here](https://developers.themoviedb.org/3/getting-started/introduction) to obtain api key

Then, put the key inside `local.properties`
```
# default local.properties content
# ...

API_KEY="put the key here"

```
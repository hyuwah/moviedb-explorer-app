package dev.hyuwah.moviedbexplorer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepository
import dev.hyuwah.moviedbexplorer.presentation.utils.ActionStateLiveData

class HomeViewModel(
    private val repo: MovieDbRepository,
    movieListMapper: MovieListMapper
) : ViewModel() {

    val popularMovies = ActionStateLiveData(viewModelScope, movieListMapper) {
        repo.getPopularMovies()
    }

    val topRatedMovies = ActionStateLiveData(viewModelScope, movieListMapper) {
        repo.getTopRatedMovies()
    }

    val nowPlayingMovies = ActionStateLiveData(viewModelScope, movieListMapper) {
        repo.getNowPlayingMovies()
    }


}
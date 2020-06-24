package dev.hyuwah.moviedbexplorer.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepository
import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.MovieItemMapper
import kotlinx.coroutines.flow.map

class FavoritesViewModel(
    repo: MovieDbRepository,
    private val movieItemMapper: MovieItemMapper
) : ViewModel() {

    val favorites = repo.getFavoriteMovies()
        .map { it.map { movieItemMapper.map(it) } }
        .asLiveData()

}
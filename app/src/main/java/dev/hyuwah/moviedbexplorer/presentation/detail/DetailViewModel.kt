package dev.hyuwah.moviedbexplorer.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepository
import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.FavoriteItemMapper
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel
import dev.hyuwah.moviedbexplorer.presentation.utils.ActionStateLiveData
import dev.hyuwah.moviedbexplorer.presentation.utils.LiveEvent
import dev.hyuwah.moviedbexplorer.presentation.utils.MutableLiveEvent
import dev.hyuwah.moviedbexplorer.presentation.utils.postEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repo: MovieDbRepository,
    reviewListMapper: ReviewListMapper,
    private val favoriteItemMapper: FavoriteItemMapper
) : ViewModel() {

    lateinit var movieItem: MovieItemModel

    private val _isFavorite = MutableLiveData(false)
    val isFavorite = _isFavorite as LiveData<Boolean>

    private val _snackbar = MutableLiveEvent<String>()
    val snackbar = _snackbar as LiveEvent<String>

    val reviews = ActionStateLiveData(viewModelScope, reviewListMapper) {
        repo.getMovieReviews(movieItem.id.toString())
    }

    /**
     * Entry Point
     */
    fun init(movieItem: MovieItemModel) {
        this.movieItem = movieItem
        reviews.load()
        checkIsFavorite()
    }

    fun onFavoriteClick() {
        if (_isFavorite.value == true) {
            removeFromFavorite()
        } else {
            addToFavorite()
        }
    }

    private fun checkIsFavorite() {
        viewModelScope.launch {
            repo.getFavoritedMovieById(movieItem.id).collect {
                _isFavorite.postValue(it != null)
            }
        }
    }

    private fun addToFavorite() {
        viewModelScope.launch {
            repo.addFavoriteMovie(favoriteItemMapper.map(movieItem))
            _snackbar.postEvent("Added to favorite")
        }
    }

    private fun removeFromFavorite() {
        viewModelScope.launch {
            repo.removeFavoriteMovie(favoriteItemMapper.map(movieItem))
            _snackbar.postEvent("Removed from favorite")
        }
    }
}
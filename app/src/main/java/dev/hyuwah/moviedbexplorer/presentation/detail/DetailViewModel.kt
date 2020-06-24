package dev.hyuwah.moviedbexplorer.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieReviewResponse
import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepository
import dev.hyuwah.moviedbexplorer.presentation.utils.ActionStateParamLiveData

class DetailViewModel(
    private val repo: MovieDbRepository,
    reviewListMapper: ReviewListMapper
) : ViewModel() {

    fun load(movieId: Int) {
        reviews.load(movieId.toString())
    }

    val reviews = ActionStateParamLiveData<MovieReviewResponse, String, List<ReviewItemModel>>(
        viewModelScope, reviewListMapper
    ) {
        repo.getMovieReviews(it)
    }
}
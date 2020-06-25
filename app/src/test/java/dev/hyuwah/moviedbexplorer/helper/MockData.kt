package dev.hyuwah.moviedbexplorer.helper

import dev.hyuwah.moviedbexplorer.data.local.entity.FavoriteMovie
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieListResponse
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieReviewResponse
import dev.hyuwah.moviedbexplorer.presentation.detail.ReviewItemModel
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel
import kotlinx.coroutines.flow.flowOf
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object MockData {

    val movieItemResponse = MovieListResponse.MovieItemResponse(
        releaseDate = "2020-02-02", title = "Movie Title"
    )

    val movieListResponse = MovieListResponse(
        0, (0 until 10).map { movieItemResponse.copy(id = it, title = "Movie Title $it") }, 1, 10
    )

    val emptyMovieListResponse = MovieListResponse(
        0, emptyList(), 1, 0
    )

    val reviewItemResponse = MovieReviewResponse.MovieReviewItemResponse()

    val movieReviewResponse = MovieReviewResponse(
        0, 1, (0 until 10).map { reviewItemResponse }, 1, 10
    )

    val emptyReviewResponse = MovieReviewResponse(
        0, 1, emptyList(), 0, 0
    )

    val reviewItemModel = ReviewItemModel(
        "",
        "",
        "",
        ""
    )

    val listReviewItemModel =
        (0 until 10).map { reviewItemModel.copy(id = it.toString()) }

    val movieItemModel = MovieItemModel(
        0,
        "Movie Title",
        "2020-02-02",
        "",
        "",
        "",
        0.0,
        0.0,
        0
    )

    val listMovieItemModel =
        (0 until 10).map { movieItemModel.copy(id = it, title = "Movie Title $it") }

    val errorMovieListResponse = Response.error<MovieListResponse>(400, "".toResponseBody())

    val favoriteMovie = FavoriteMovie(
        0,
        "",
        "",
        "",
        "",
        "",
        0.0,
        0.0,
        0
    )
    val favoriteMovies =
        (0 until 10).map { favoriteMovie.copy(movieId = it, title = "Movie Title $it") }
    val flowFavoriteMovies = flowOf(favoriteMovies)

    fun MovieItemModel.times(times: Int): List<MovieItemModel> {
        return (0 until times).map { this }
    }
}
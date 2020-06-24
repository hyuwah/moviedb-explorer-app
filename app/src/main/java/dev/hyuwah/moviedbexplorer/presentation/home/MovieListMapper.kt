package dev.hyuwah.moviedbexplorer.presentation.home

import dev.hyuwah.moviedbexplorer.data.remote.NetworkConstant
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieListResponse
import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.Mapper
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieListMapper :
    Mapper<MovieListResponse, List<MovieItemModel>> {

    override fun map(dataIn: MovieListResponse): List<MovieItemModel> {
        return dataIn.results.orEmpty().map {
            MovieItemModel(
                it.id,
                it.title,
                it.releaseDate.orEmpty().asReadableDate(),
                it.backdropPath.orEmpty().asBackdropUrl(),
                it.posterPath.orEmpty().asPosterUrl(),
                it.overview.orEmpty(),
                it.popularity,
                it.voteAverage,
                it.voteCount
            )
        }
    }

    private fun String.asReadableDate(): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val localDate = LocalDate.parse(this, formatter)
            val newFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            newFormatter.format(localDate)
        } catch (e: Exception) {
            ""
        }
    }

    private fun String.asPosterUrl() =
        if (this.isEmpty()) NetworkConstant.DEFAULT_POSTER_URL
        else NetworkConstant.IMG_BASE_URL + "w342" + this

    private fun String.asBackdropUrl() =
        if (this.isEmpty()) NetworkConstant.DEFAULT_BACKDROP_URL
        else NetworkConstant.IMG_BASE_URL + "w780" + this
}
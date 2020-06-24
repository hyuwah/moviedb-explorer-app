package dev.hyuwah.moviedbexplorer.presentation.shared.mapper

import dev.hyuwah.moviedbexplorer.data.local.entity.FavoriteMovie
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel

class FavoriteItemMapper : Mapper<MovieItemModel, FavoriteMovie> {

    override fun map(dataIn: MovieItemModel): FavoriteMovie {
        return FavoriteMovie(
            dataIn.id,
            dataIn.title,
            dataIn.posterPath,
            dataIn.backdropPath,
            dataIn.releaseDate,
            dataIn.overview,
            dataIn.popularity,
            dataIn.voteAverage,
            dataIn.voteCount
        )
    }

}
package dev.hyuwah.moviedbexplorer.presentation.favorite

import dev.hyuwah.moviedbexplorer.data.local.entity.FavoriteMovie
import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.Mapper
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel

class MovieItemMapper :
    Mapper<FavoriteMovie, MovieItemModel> {

    override fun map(dataIn: FavoriteMovie): MovieItemModel {
        return MovieItemModel(
            dataIn.movieId,
            dataIn.title,
            dataIn.releaseDate,
            dataIn.backdropUrl,
            dataIn.posterUrl,
            dataIn.overview,
            dataIn.popularity,
            dataIn.voteAverage,
            dataIn.voteCount
        )
    }

}
package dev.hyuwah.moviedbexplorer.presentation.detail

import dev.hyuwah.moviedbexplorer.data.remote.model.MovieReviewResponse
import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.Mapper

class ReviewListMapper : Mapper<MovieReviewResponse, List<ReviewItemModel>> {
    override fun map(dataIn: MovieReviewResponse): List<ReviewItemModel> {
        return dataIn.results.orEmpty().map {
            ReviewItemModel(it.id, it.author, it.content, it.url)
        }
    }

}
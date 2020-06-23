package dev.hyuwah.moviedbexplorer.presentation.shared.model

import androidx.recyclerview.widget.DiffUtil

data class MovieItemModel(
    var id: Int
) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<MovieItemModel>() {
            override fun areItemsTheSame(
                oldItem: MovieItemModel,
                newItem: MovieItemModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieItemModel,
                newItem: MovieItemModel
            ): Boolean = oldItem == newItem
        }
    }
}
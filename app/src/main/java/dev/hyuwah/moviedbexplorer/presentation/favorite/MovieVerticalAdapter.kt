package dev.hyuwah.moviedbexplorer.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dev.hyuwah.moviedbexplorer.databinding.ListItemMovieVerticalBinding
import dev.hyuwah.moviedbexplorer.presentation.shared.MovieItemClick
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel

class MovieVerticalAdapter(
    private val onItemClick: MovieItemClick
) : ListAdapter<MovieItemModel, MovieVerticalAdapter.ViewHolder>(
    MovieItemModel.DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemMovieVerticalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    inner class ViewHolder(
        private val binding: ListItemMovieVerticalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItemModel, onItemClick: MovieItemClick) = with(binding) {
            root.setOnClickListener { onItemClick(item) }
            ivPoster.load(item.posterPath) {
                crossfade(true)
                transformations(RoundedCornersTransformation(16f, 0f, 16f, 0f))
            }
            tvTitle.text = item.title
            tvReleaseDate.text = "Release Date ${item.releaseDate}"
            tvDesc.text = item.overview
            tvRating.text = item.voteAverage.toString()
            tvVoteCount.text = "(${item.voteCount} votes)"
        }
    }

}
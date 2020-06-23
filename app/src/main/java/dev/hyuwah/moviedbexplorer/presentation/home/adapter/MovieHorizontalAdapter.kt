package dev.hyuwah.moviedbexplorer.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dev.hyuwah.moviedbexplorer.R
import dev.hyuwah.moviedbexplorer.databinding.ListItemMovieHorizontalBinding
import dev.hyuwah.moviedbexplorer.presentation.home.adapter.MovieHorizontalAdapter.ViewHolder
import dev.hyuwah.moviedbexplorer.presentation.shared.MovieItemClick
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel

class MovieHorizontalAdapter(
    private val onItemClick: MovieItemClick
) : ListAdapter<MovieItemModel, ViewHolder>(
    MovieItemModel.DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemMovieHorizontalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    inner class ViewHolder(
        private val binding: ListItemMovieHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItemModel, onItemClick: MovieItemClick) = with(binding) {
            root.setOnClickListener { onItemClick(item) }
            ivPoster.load(R.drawable.placeholder_poster_portrait) {
                crossfade(true)
                placeholder(R.drawable.placeholder_poster_portrait)
                transformations(RoundedCornersTransformation(16f))
            }
            tvTitle.text = "Title ${item.id}"
            tvReleaseDate.text = "Release Date ${item.id}"
        }
    }

}
package dev.hyuwah.moviedbexplorer.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import dev.hyuwah.moviedbexplorer.R
import dev.hyuwah.moviedbexplorer.databinding.ListItemBigBannerBinding
import dev.hyuwah.moviedbexplorer.presentation.home.adapter.BigBannerAdapter.ViewHolder
import dev.hyuwah.moviedbexplorer.presentation.shared.MovieItemClick
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel

class BigBannerAdapter(
    private val onItemClick: MovieItemClick
) : ListAdapter<MovieItemModel, ViewHolder>(
    MovieItemModel.DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemBigBannerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    inner class ViewHolder(
        private val binding: ListItemBigBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItemModel, onItemClick: MovieItemClick) = with(binding) {
            root.setOnClickListener { onItemClick(item) }
            ivBanner.load(R.drawable.placeholder_poster_landscape) {
                crossfade(true)
                placeholder(R.drawable.placeholder_poster_landscape)
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

}
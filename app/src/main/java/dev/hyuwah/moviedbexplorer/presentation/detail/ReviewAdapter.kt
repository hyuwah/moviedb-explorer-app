package dev.hyuwah.moviedbexplorer.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.hyuwah.moviedbexplorer.databinding.ListItemReviewBinding

class ReviewAdapter :
    ListAdapter<ReviewItemModel, ReviewAdapter.ViewHolder>(ReviewItemModel.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemReviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(
        private val binding: ListItemReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReviewItemModel) = with(binding) {
            tvAuthor.text = item.author
            tvContent.text = item.content
        }

    }

}
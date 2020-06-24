package dev.hyuwah.moviedbexplorer.presentation.detail

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewItemModel(
    var id: String,
    var author: String,
    var content: String,
    var url: String
) : Parcelable {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ReviewItemModel>() {
            override fun areItemsTheSame(
                oldItem: ReviewItemModel,
                newItem: ReviewItemModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ReviewItemModel,
                newItem: ReviewItemModel
            ): Boolean = oldItem == newItem
        }
    }
}
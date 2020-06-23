package dev.hyuwah.moviedbexplorer.data.remote.model


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieItemResponse> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)
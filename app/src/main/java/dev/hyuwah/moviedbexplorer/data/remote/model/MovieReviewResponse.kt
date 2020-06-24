package dev.hyuwah.moviedbexplorer.data.remote.model


import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieReviewItemResponse>? = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
) {
    data class MovieReviewItemResponse(
        @SerializedName("author")
        val author: String = "",
        @SerializedName("content")
        val content: String = "",
        @SerializedName("id")
        val id: String = "",
        @SerializedName("url")
        val url: String = ""
    )
}
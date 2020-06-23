package dev.hyuwah.moviedbexplorer.data.remote.model


import com.google.gson.annotations.SerializedName

data class MovieNowPlayingResponse(
    @SerializedName("dates")
    val dates: Dates = Dates(),
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieItemResponse> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
) {
    data class Dates(
        @SerializedName("maximum")
        val maximum: String = "",
        @SerializedName("minimum")
        val minimum: String = ""
    )
}
package dev.hyuwah.moviedbexplorer.data.remote

import dev.hyuwah.moviedbexplorer.data.remote.model.MovieListResponse
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieServiceApi {

    @GET("movie/popular?region=US")
    suspend fun getPopularMovies(): Response<MovieListResponse>

    @GET("movie/top_rated?region=US")
    suspend fun getTopRatedMovies(): Response<MovieListResponse>

    @GET("movie/now_playing?region=US")
    suspend fun getNowPlayingMovies(): Response<MovieListResponse>

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReviews(
        @Path("movieId") movieId: String
    ): Response<MovieReviewResponse>

}
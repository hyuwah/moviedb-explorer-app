package dev.hyuwah.moviedbexplorer.data.repository

import dev.hyuwah.moviedbexplorer.data.remote.model.MovieListResponse
import retrofit2.Response

interface MovieDbRepository {
    suspend fun getPopularMovies(): Response<MovieListResponse>
    suspend fun getTopRatedMovies(): Response<MovieListResponse>
    suspend fun getNowPlayingMovies(): Response<MovieListResponse>
}
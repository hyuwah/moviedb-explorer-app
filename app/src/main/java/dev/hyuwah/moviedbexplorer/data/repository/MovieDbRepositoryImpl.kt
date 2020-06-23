package dev.hyuwah.moviedbexplorer.data.repository

import dev.hyuwah.moviedbexplorer.data.remote.MovieServiceApi
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieListResponse
import retrofit2.Response

class MovieDbRepositoryImpl(
    private val serviceApi: MovieServiceApi
) : MovieDbRepository {

    override suspend fun getPopularMovies(): Response<MovieListResponse> {
        return serviceApi.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): Response<MovieListResponse> {
        return serviceApi.getTopRatedMovies()
    }

    override suspend fun getNowPlayingMovies(): Response<MovieListResponse> {
        return serviceApi.getNowPlayingMovies()
    }

}
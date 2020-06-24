package dev.hyuwah.moviedbexplorer.data.repository

import dev.hyuwah.moviedbexplorer.data.local.entity.FavoriteMovie
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieListResponse
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieReviewResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieDbRepository {
    suspend fun getPopularMovies(): Response<MovieListResponse>
    suspend fun getTopRatedMovies(): Response<MovieListResponse>
    suspend fun getNowPlayingMovies(): Response<MovieListResponse>
    suspend fun getMovieReviews(movieId: String): Response<MovieReviewResponse>

    fun getFavoritedMovieById(id: Int): Flow<FavoriteMovie?>
    fun getFavoriteMovies(): Flow<List<FavoriteMovie>>
    suspend fun addFavoriteMovie(movie: FavoriteMovie)
    suspend fun removeFavoriteMovie(movie: FavoriteMovie)
}
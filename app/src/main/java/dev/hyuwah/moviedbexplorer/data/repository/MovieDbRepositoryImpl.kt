package dev.hyuwah.moviedbexplorer.data.repository

import dev.hyuwah.moviedbexplorer.data.local.FavoritesDao
import dev.hyuwah.moviedbexplorer.data.local.entity.FavoriteMovie
import dev.hyuwah.moviedbexplorer.data.remote.MovieServiceApi
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieListResponse
import dev.hyuwah.moviedbexplorer.data.remote.model.MovieReviewResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieDbRepositoryImpl(
    private val serviceApi: MovieServiceApi,
    private val favoriteDao: FavoritesDao
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

    override suspend fun getMovieReviews(movieId: String): Response<MovieReviewResponse> {
        return serviceApi.getMovieReviews(movieId)
    }

    override fun getFavoritedMovieById(id: Int): Flow<FavoriteMovie?> {
        return favoriteDao.getFavoriteById(id)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return favoriteDao.getFavoriteMovies()
    }

    override suspend fun addFavoriteMovie(movie: FavoriteMovie) {
        favoriteDao.insert(movie)
    }

    override suspend fun removeFavoriteMovie(movie: FavoriteMovie) {
        favoriteDao.delete(movie.movieId)
    }

}
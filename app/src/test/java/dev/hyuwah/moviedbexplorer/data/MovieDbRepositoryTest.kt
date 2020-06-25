package dev.hyuwah.moviedbexplorer.data

import dev.hyuwah.moviedbexplorer.data.local.FavoritesDao
import dev.hyuwah.moviedbexplorer.data.local.entity.FavoriteMovie
import dev.hyuwah.moviedbexplorer.data.remote.MovieServiceApi
import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepository
import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepositoryImpl
import dev.hyuwah.moviedbexplorer.helper.BaseTest
import dev.hyuwah.moviedbexplorer.helper.MockData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieDbRepositoryTest : BaseTest() {

    @MockK
    private lateinit var mockServiceApi: MovieServiceApi

    @MockK
    private lateinit var mockFavoritesDao: FavoritesDao

    private lateinit var repo: MovieDbRepository

    override fun setup() {
        super.setup()
        repo = MovieDbRepositoryImpl(mockServiceApi, mockFavoritesDao)
    }

    @Test
    fun `Get popular movies`() {
        // Given
        val response = Response.success(MockData.movieListResponse)
        coEvery { mockServiceApi.getPopularMovies() } returns response

        runBlockingTest {
            // When
            val result = repo.getPopularMovies()
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun `Get top rated movies`() {
        // Given
        val response = Response.success(MockData.movieListResponse)
        coEvery { mockServiceApi.getTopRatedMovies() } returns response

        runBlockingTest {
            // When
            val result = repo.getTopRatedMovies()
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun `Get now playing movies`() {
        // Given
        val response = Response.success(MockData.movieListResponse)
        coEvery { mockServiceApi.getNowPlayingMovies() } returns response

        runBlockingTest {
            // When
            val result = repo.getNowPlayingMovies()
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun `Get movie review`() {
        // Given
        val response = Response.success(MockData.movieReviewResponse)
        coEvery { mockServiceApi.getMovieReviews(any()) } returns response

        runBlockingTest {
            // When
            val result = repo.getMovieReviews("123")
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun `Get favorite movie by id exist`() {
        // Given
        val response = flowOf(MockData.favoriteMovie)
        every { mockFavoritesDao.getFavoriteById(any()) } returns response

        // When
        val result = repo.getFavoritedMovieById(123)

        // Then
        Assert.assertEquals(response, result)
    }

    @Test
    fun `Get favorite movie by id not exist`() {
        // Given
        val response = flowOf<FavoriteMovie?>(null)
        every { mockFavoritesDao.getFavoriteById(any()) } returns response

        // When
        val result = repo.getFavoritedMovieById(123)

        // Then
        Assert.assertEquals(response, result)
    }

    @Test
    fun `Get favorite movies exist`() {
        // Given
        val response = MockData.flowFavoriteMovies
        every { mockFavoritesDao.getFavoriteMovies() } returns response

        // When
        val result = repo.getFavoriteMovies()

        // Then
        Assert.assertEquals(response, result)
    }

    @Test
    fun `Get favorite movies not exist`() {
        // Given
        val response = flowOf<List<FavoriteMovie>>(emptyList())
        every { mockFavoritesDao.getFavoriteMovies() } returns response

        // When
        val result = repo.getFavoriteMovies()

        // Then
        Assert.assertEquals(response, result)
    }

    @Test
    fun `Add favorite movie`() {
        // Given
        val input = MockData.favoriteMovie
        coEvery { mockFavoritesDao.insert(any()) } coAnswers { println("Inserting $input") }

        runBlockingTest {
            // When
            repo.addFavoriteMovie(input)

            // Then
            coVerify { mockFavoritesDao.insert(input) }
        }
    }

    @Test
    fun `Remove favorite movie`() {
        // Given
        val input = MockData.favoriteMovie
        coEvery { mockFavoritesDao.delete(any()) } coAnswers { println("Removing $input") }

        runBlockingTest {
            // When
            repo.removeFavoriteMovie(input)

            // Then
            coVerify { mockFavoritesDao.delete(input.movieId) }
        }
    }


}
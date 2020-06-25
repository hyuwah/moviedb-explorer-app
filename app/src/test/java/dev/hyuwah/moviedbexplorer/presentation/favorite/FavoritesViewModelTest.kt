package dev.hyuwah.moviedbexplorer.presentation.favorite

import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepository
import dev.hyuwah.moviedbexplorer.helper.BaseTest
import dev.hyuwah.moviedbexplorer.helper.MockData
import dev.hyuwah.moviedbexplorer.helper.MockData.times
import dev.hyuwah.moviedbexplorer.helper.TestHelper.getMockObserver
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoritesViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: MovieDbRepository

    @MockK
    private lateinit var mockMapper: MovieItemMapper
    private lateinit var viewModel: FavoritesViewModel


    @Test
    fun `Should get empty favorite movies`() {
        // When
        coEvery { mockRepo.getFavoriteMovies() } returns flowOf(emptyList())
        every { mockMapper.map(any()) } returns MockData.movieItemModel
        viewModel = FavoritesViewModel(mockRepo, mockMapper)

        val observer = getMockObserver<List<MovieItemModel>>()
        viewModel.favorites.observeForever(observer)

        // Then
        Assert.assertEquals(
            emptyList<MovieItemModel>(),
            viewModel.favorites.value
        )
    }

    @Test
    fun `Should get 10 favorite movies`() {
        // When
        coEvery { mockRepo.getFavoriteMovies() } returns MockData.flowFavoriteMovies
        every { mockMapper.map(any()) } returns MockData.movieItemModel
        viewModel = FavoritesViewModel(mockRepo, mockMapper)

        val observer = getMockObserver<List<MovieItemModel>>()
        viewModel.favorites.observeForever(observer)

        // Then
        Assert.assertEquals(
            MockData.movieItemModel.times(MockData.favoriteMovies.size),
            viewModel.favorites.value
        )
    }

}
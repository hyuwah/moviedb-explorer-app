package dev.hyuwah.moviedbexplorer.presentation.detail

import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepository
import dev.hyuwah.moviedbexplorer.helper.BaseTest
import dev.hyuwah.moviedbexplorer.helper.MockData
import dev.hyuwah.moviedbexplorer.helper.MockData.emptyReviewResponse
import dev.hyuwah.moviedbexplorer.helper.MockData.favoriteMovie
import dev.hyuwah.moviedbexplorer.helper.MockData.listReviewItemModel
import dev.hyuwah.moviedbexplorer.helper.MockData.movieReviewResponse
import dev.hyuwah.moviedbexplorer.helper.TestHelper
import dev.hyuwah.moviedbexplorer.presentation.detail.mapper.FavoriteItemMapper
import dev.hyuwah.moviedbexplorer.presentation.detail.mapper.ReviewListMapper
import dev.hyuwah.moviedbexplorer.presentation.utils.Event
import dev.hyuwah.moviedbexplorer.presentation.utils.UIState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class DetailViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: MovieDbRepository

    @MockK
    private lateinit var mockFavoriteMapper: FavoriteItemMapper

    @MockK
    private lateinit var mockReviewMapper: ReviewListMapper
    private lateinit var viewModel: DetailViewModel

    override fun setup() {
        super.setup()
        viewModel = DetailViewModel(mockRepo, mockReviewMapper, mockFavoriteMapper)
        viewModel.init(MockData.movieItemModel)
    }

    @Test
    fun `Load review, state should be loading then Success Empty`() {
        val observer = TestHelper.getMockStateObserver<List<ReviewItemModel>?>()
        viewModel.reviews.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getMovieReviews(any()) } returns Response.success(emptyReviewResponse)
        every { mockReviewMapper.map(any()) } returns emptyList()

        // When
        viewModel.reviews.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(UIState.Success(emptyList()))
        }
    }

    @Test
    fun `Load review, state should be loading then Success with Result`() {
        val observer = TestHelper.getMockStateObserver<List<ReviewItemModel>?>()
        viewModel.reviews.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getMovieReviews(any()) } returns Response.success(movieReviewResponse)
        every { mockReviewMapper.map(any()) } returns listReviewItemModel

        // When
        viewModel.reviews.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(UIState.Success(listReviewItemModel))
        }
    }

    @Test
    fun `Movie should be flagged as favorite`() {
        val observer = TestHelper.getMockObserver<Boolean>()
        viewModel.isFavorite.observeForever(observer)

        // Given
        coEvery { mockRepo.getFavoritedMovieById(any()) } returns flowOf(favoriteMovie)

        // When
        viewModel.checkIsFavorite()

        // Then
        verifySequence {
            observer.onChanged(false) // Default value
            observer.onChanged(true)
        }
    }

    @Test
    fun `Movie should not be flagged as favorite`() {
        val observer = TestHelper.getMockObserver<Boolean>()
        viewModel.isFavorite.observeForever(observer)

        // Given
        coEvery { mockRepo.getFavoritedMovieById(any()) } returns flowOf(null)

        // When
        viewModel.checkIsFavorite()

        // Then
        verifySequence {
            observer.onChanged(false) // Default value
            observer.onChanged(false)
        }
    }

    @Test
    fun `On favorite click, movie's that not favorite should be added as favorite`() {
        val observer = TestHelper.getMockObserver<Event<String>>()
        viewModel.snackbar.observeForever(observer)

        // Given
        coEvery { mockRepo.getFavoritedMovieById(any()) } returns flowOf(null)
        every { mockFavoriteMapper.map(any()) } returns favoriteMovie
        coEvery { mockRepo.addFavoriteMovie(any()) } answers { println("Movie added to favorite") }
        viewModel.checkIsFavorite()

        // When
        viewModel.onFavoriteClick()

        // Then
        verify {
            observer.onChanged(viewModel.snackbar.value)
        }
        Assert.assertEquals("Added to favorite", viewModel.snackbar.value?.peekContent())
    }

    @Test
    fun `On favorite click, movie's that already favorite should be removed from favorite`() {
        val observer = TestHelper.getMockObserver<Event<String>>()
        viewModel.snackbar.observeForever(observer)

        // Given
        coEvery { mockRepo.getFavoritedMovieById(any()) } returns flowOf(favoriteMovie)
        every { mockFavoriteMapper.map(any()) } returns favoriteMovie
        coEvery { mockRepo.removeFavoriteMovie(any()) } answers { println("Movie removed from favorite") }
        viewModel.checkIsFavorite()

        // When
        viewModel.onFavoriteClick()

        // Then
        verify {
            observer.onChanged(viewModel.snackbar.value)
        }
        Assert.assertEquals("Removed from favorite", viewModel.snackbar.value?.peekContent())
    }


}
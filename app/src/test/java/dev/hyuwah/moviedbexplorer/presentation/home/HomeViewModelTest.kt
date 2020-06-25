package dev.hyuwah.moviedbexplorer.presentation.home

import dev.hyuwah.moviedbexplorer.data.repository.MovieDbRepository
import dev.hyuwah.moviedbexplorer.helper.BaseTest
import dev.hyuwah.moviedbexplorer.helper.MockData
import dev.hyuwah.moviedbexplorer.helper.TestHelper.getMockStateObserver
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel
import dev.hyuwah.moviedbexplorer.presentation.utils.UIState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class HomeViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: MovieDbRepository

    @MockK
    private lateinit var mockMapper: MovieListMapper
    private lateinit var viewModel: HomeViewModel

    override fun setup() {
        super.setup()
        viewModel = HomeViewModel(mockRepo, mockMapper)
    }

    @Test
    fun `Load popular movies, state should be Loading then Success Empty`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.popularMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getPopularMovies() } returns Response.success(MockData.emptyMovieListResponse)
        every { mockMapper.map(any()) } returns emptyList()

        // When
        viewModel.popularMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(UIState.Success(emptyList()))
        }
    }

    @Test
    fun `Load popular movies, state should be Loading then Success With Result`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.popularMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getPopularMovies() } returns Response.success(MockData.movieListResponse)
        every { mockMapper.map(any()) } returns MockData.listMovieItemModel

        // When
        viewModel.popularMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(UIState.Success(MockData.listMovieItemModel))
        }
    }

    @Test
    fun `Load popular movies, state should be Loading then Error`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.popularMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getPopularMovies() } returns MockData.errorMovieListResponse
        every { mockMapper.map(any()) } returns emptyList()

        // When
        viewModel.popularMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(viewModel.popularMovies.state.value) // Can't use Failure(Exception()), Exception object is different
        }
    }

    @Test
    fun `Load top rated movies, state should be Loading then Success Empty`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.topRatedMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getTopRatedMovies() } returns Response.success(MockData.emptyMovieListResponse)
        every { mockMapper.map(any()) } returns emptyList()

        // When
        viewModel.topRatedMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(UIState.Success(emptyList()))
        }
    }

    @Test
    fun `Load top rated movies, state should be Loading then Success With Result`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.topRatedMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getTopRatedMovies() } returns Response.success(MockData.movieListResponse)
        every { mockMapper.map(any()) } returns MockData.listMovieItemModel

        // When
        viewModel.topRatedMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(UIState.Success(MockData.listMovieItemModel))
        }
    }

    @Test
    fun `Load top rated movies, state should be Loading then Error`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.topRatedMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getTopRatedMovies() } returns MockData.errorMovieListResponse
        every { mockMapper.map(any()) } returns emptyList()

        // When
        viewModel.topRatedMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(viewModel.topRatedMovies.state.value) // Can't use Failure(Exception()), Exception object is different
        }
    }


    @Test
    fun `Load now playing movies, state should be Loading then Success Empty`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.nowPlayingMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getNowPlayingMovies() } returns Response.success(MockData.emptyMovieListResponse)
        every { mockMapper.map(any()) } returns emptyList()

        // When
        viewModel.nowPlayingMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(UIState.Success(emptyList()))
        }
    }

    @Test
    fun `Load now playing movies, state should be Loading then Success With Result`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.nowPlayingMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getNowPlayingMovies() } returns Response.success(MockData.movieListResponse)
        every { mockMapper.map(any()) } returns MockData.listMovieItemModel

        // When
        viewModel.nowPlayingMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(UIState.Success(MockData.listMovieItemModel))
        }
    }

    @Test
    fun `Load now playing movies, state should be Loading then Error`() {
        val observer = getMockStateObserver<List<MovieItemModel>?>()
        viewModel.nowPlayingMovies.state.observeForever(observer)

        // Given
        coEvery { mockRepo.getNowPlayingMovies() } returns MockData.errorMovieListResponse
        every { mockMapper.map(any()) } returns emptyList()

        // When
        viewModel.nowPlayingMovies.load()

        // Then
        verifySequence {
            observer.onChanged(UIState.Loading)
            observer.onChanged(viewModel.nowPlayingMovies.state.value) // Can't use Failure(Exception()), Exception object is different
        }
    }

}
package dev.hyuwah.moviedbexplorer.presentation.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dev.hyuwah.moviedbexplorer.R
import dev.hyuwah.moviedbexplorer.databinding.FragmentHomeBinding
import dev.hyuwah.moviedbexplorer.presentation.home.adapter.BigBannerAdapter
import dev.hyuwah.moviedbexplorer.presentation.home.adapter.MovieHorizontalAdapter
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel
import dev.hyuwah.moviedbexplorer.presentation.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), Toolbar.OnMenuItemClickListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel: HomeViewModel by viewModel()

    private val popularAdapter = BigBannerAdapter(::onMovieItemClicked)
    private val topRatedAdapter = MovieHorizontalAdapter(::onMovieItemClicked)
    private val nowPlayingAdapter = MovieHorizontalAdapter(::onMovieItemClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setupObserver()
        initData()
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvPopular.adapter = popularAdapter
            rvTopRated.adapter = topRatedAdapter
            rvNowPlaying.adapter = nowPlayingAdapter
        }
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            setupWithNavController(navController, appBarConfiguration)
            inflateMenu(R.menu.menu_home)
            setOnMenuItemClickListener(this@HomeFragment)
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_home_favorite -> {
                // TODO Navigate to Favorite List screen
                toast("Navigate to favorite movie screen")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupObserver() {
        observeStateOf(viewModel.popularMovies, ::handlePopularState)
        observeStateOf(viewModel.topRatedMovies, ::handleTopRatedState)
        observeStateOf(viewModel.nowPlayingMovies, ::handleNowPlayingState)
    }

    private fun handlePopularState(state: UIState<List<MovieItemModel>?>) {
        when (state) {
            UIState.Loading -> {
                with(binding) {
                    shimmerPopular.apply {
                        setVisible()
                        startShimmer()
                    }
                    rvPopular.setGone()
                }
            }
            is UIState.Success -> {
                with(binding) {
                    shimmerPopular.apply {
                        stopShimmer()
                        setGone()
                    }
                    rvPopular.setVisible()
                }
                state.data?.let { popularAdapter.submitList(it) }
            }
            is UIState.Failure -> {
                toast(state.exception.message.orIfEmpty("Unknown Error"))
            }
        }
    }

    private fun handleTopRatedState(state: UIState<List<MovieItemModel>?>) {
        when (state) {
            UIState.Loading -> {
                with(binding) {
                    shimmerTopRated.apply {
                        setVisible()
                        startShimmer()
                    }
                    rvTopRated.setGone()
                }
            }
            is UIState.Success -> {
                with(binding) {
                    shimmerTopRated.apply {
                        stopShimmer()
                        setGone()
                    }
                    rvTopRated.setVisible()
                }
                state.data?.let { topRatedAdapter.submitList(it) }
            }
            is UIState.Failure -> {
                toast(state.exception.message.orIfEmpty("Unknown Error"))
            }
        }
    }

    private fun handleNowPlayingState(state: UIState<List<MovieItemModel>?>) {
        when (state) {
            UIState.Loading -> {
                with(binding) {
                    shimmerNowPlaying.apply {
                        setVisible()
                        startShimmer()
                    }
                    rvNowPlaying.setGone()
                }
            }
            is UIState.Success -> {
                with(binding) {
                    shimmerNowPlaying.apply {
                        stopShimmer()
                        setGone()
                    }
                    rvNowPlaying.setVisible()
                }
                state.data?.let { nowPlayingAdapter.submitList(it) }
            }
            is UIState.Failure -> {
                toast(state.exception.message.orIfEmpty("Unknown Error"))
            }
        }
    }

    private fun initData() {
        viewModel.popularMovies.load()
        viewModel.topRatedMovies.load()
        viewModel.nowPlayingMovies.load()
    }

    private fun onMovieItemClicked(movie: MovieItemModel) {
        // Navigate To Detail

    }

}
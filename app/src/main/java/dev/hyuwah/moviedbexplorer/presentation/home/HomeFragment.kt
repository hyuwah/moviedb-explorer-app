package dev.hyuwah.moviedbexplorer.presentation.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
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
        setupListener()
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
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToFavoritesFragment()
                )
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
                    showLoading(shimmerPopular, rvPopular, layoutPopularError.root)
                }
            }
            is UIState.Success -> {
                with(binding) {
                    hideLoading(shimmerPopular, rvPopular)
                    state.data?.let { popularAdapter.submitList(it) }
                }
            }
            is UIState.Failure -> {
                with(binding) {
                    hideLoading(shimmerPopular, rvPopular, false)
                    layoutPopularError.root.setVisible()
                }
            }
        }
    }

    private fun handleTopRatedState(state: UIState<List<MovieItemModel>?>) {
        when (state) {
            UIState.Loading -> {
                with(binding) {
                    showLoading(shimmerTopRated, rvTopRated, layoutTopRatedError.root)
                }
            }
            is UIState.Success -> {
                with(binding) {
                    hideLoading(shimmerTopRated, rvTopRated)
                    state.data?.let { topRatedAdapter.submitList(it) }
                }
            }
            is UIState.Failure -> {
                with(binding) {
                    hideLoading(shimmerTopRated, rvTopRated, false)
                    layoutTopRatedError.root.setVisible()
                }
            }
        }
    }

    private fun handleNowPlayingState(state: UIState<List<MovieItemModel>?>) {
        when (state) {
            UIState.Loading -> {
                with(binding) {
                    showLoading(shimmerNowPlaying, rvNowPlaying, layoutNowPlayingError.root)
                }
            }
            is UIState.Success -> {
                with(binding) {
                    hideLoading(shimmerNowPlaying, rvNowPlaying)
                    state.data?.let { nowPlayingAdapter.submitList(it) }
                }
            }
            is UIState.Failure -> {
                with(binding) {
                    hideLoading(shimmerNowPlaying, rvNowPlaying, false)
                    layoutNowPlayingError.root.setVisible()
                }
            }
        }
    }

    private fun setupListener() {
        with(binding) {
            layoutPopularError.btnReload.setOnClickListener {
                viewModel.popularMovies.load()
            }
            layoutTopRatedError.btnReload.setOnClickListener {
                viewModel.topRatedMovies.load()
            }
            layoutNowPlayingError.btnReload.setOnClickListener {
                viewModel.nowPlayingMovies.load()
            }
        }
    }

    private fun initData() {
        viewModel.popularMovies.load()
        viewModel.topRatedMovies.load()
        viewModel.nowPlayingMovies.load()
    }

    private fun onMovieItemClicked(movie: MovieItemModel) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie))
    }

    private fun showLoading(
        shimmerView: ShimmerFrameLayout,
        recyclerView: RecyclerView,
        errorView: View
    ) {
        shimmerView.setVisible()
        shimmerView.startShimmer()
        recyclerView.setGone()
        errorView.setGone()
    }

    private fun hideLoading(
        shimmerView: ShimmerFrameLayout,
        recyclerView: RecyclerView,
        showRv: Boolean = true
    ) {
        shimmerView.setGone()
        shimmerView.stopShimmer()
        if (showRv) recyclerView.setVisible()
    }

}
package dev.hyuwah.moviedbexplorer.presentation.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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
import dev.hyuwah.moviedbexplorer.presentation.utils.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home), Toolbar.OnMenuItemClickListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val navController by lazy { findNavController() }

    private val popularAdapter = BigBannerAdapter {

    }
    private val topRatedAdapter = MovieHorizontalAdapter {

    }
    private val nowPlayingAdapter = MovieHorizontalAdapter {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvPopular.adapter = popularAdapter
            rvTopRated.adapter = topRatedAdapter
            rvNowPlaying.adapter = nowPlayingAdapter

            popularAdapter.submitList(getDummyList())
            topRatedAdapter.submitList(getDummyList())
            nowPlayingAdapter.submitList(getDummyList())
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
                Toast.makeText(requireContext(), "Go To Favorite", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getDummyList(): List<MovieItemModel> {
        return listOf(
            MovieItemModel(1),
            MovieItemModel(2),
            MovieItemModel(3),
            MovieItemModel(4)
        )
    }

}
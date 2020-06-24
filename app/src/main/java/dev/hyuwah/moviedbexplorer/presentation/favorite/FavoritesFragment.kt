package dev.hyuwah.moviedbexplorer.presentation.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dev.hyuwah.moviedbexplorer.R
import dev.hyuwah.moviedbexplorer.databinding.FragmentFavoritesBinding
import dev.hyuwah.moviedbexplorer.presentation.utils.setVisibleIf
import dev.hyuwah.moviedbexplorer.presentation.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesViewModel by viewModel()
    private val navController by lazy { findNavController() }
    private val adapter = MovieVerticalAdapter {
        navController.navigate(
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(it)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.favorites.observe(viewLifecycleOwner) {
            binding.tvEmptyView.setVisibleIf(it.isEmpty())
            binding.rvFavorites.setVisibleIf(it.isNotEmpty())
            adapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvFavorites.adapter = adapter
    }

    private fun setupToolbar() {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}
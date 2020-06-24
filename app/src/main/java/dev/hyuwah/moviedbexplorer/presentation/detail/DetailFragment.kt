package dev.hyuwah.moviedbexplorer.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import coil.api.load
import dev.hyuwah.moviedbexplorer.R
import dev.hyuwah.moviedbexplorer.databinding.FragmentDetailBinding
import dev.hyuwah.moviedbexplorer.presentation.shared.model.MovieItemModel
import dev.hyuwah.moviedbexplorer.presentation.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val navController by lazy { findNavController() }
    private val detailArgs by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModel()
    private val adapter = ReviewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(detailArgs.movieItem)
        setupRecyclerView()
        setupObserver()
        viewModel.load(detailArgs.movieItem.id)
    }

    private fun setupUI(movieItem: MovieItemModel) {
        with(binding) {
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            toolbar.setupWithNavController(navController, appBarConfiguration)
            toolbar.title = movieItem.title
            ivBackdrop.load(movieItem.backdropPath) {
                crossfade(true)
                placeholder(R.drawable.placeholder_poster_landscape)
            }
            layoutImagePoster.ivDetailPoster.load(movieItem.posterPath) {
                crossfade(true)
                placeholder(R.drawable.placeholder_poster_portrait)
            }
            tvDetailRating.text = movieItem.voteAverage.toString()
            tvDetailReleaseDate.text = movieItem.releaseDate
            tvDetailVoteCount.text = "${movieItem.voteCount} votes"
            tvDetailDesc.text = movieItem.overview
        }
    }

    private fun setupRecyclerView() {
        binding.rvReviews.apply {
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
            adapter = this@DetailFragment.adapter

        }
    }

    private fun setupObserver() {
        viewModel.reviews.state.observe(viewLifecycleOwner) {
            when (it) {
                UIState.Loading -> {
                    showLoading()
                }
                is UIState.Success -> {
                    hideLoading()
                    it.data?.let {
                        binding.tvReviewTitle.text = "Review (${it.size})"
                        adapter.submitList(it)
                    }
                }
                is UIState.Failure -> {
                    toast(it.exception.message.orIfEmpty("Unknown Error"))
                }
            }
        }
    }

    private fun showLoading() {
        binding.shimmerReview.setVisible()
        binding.shimmerReview.startShimmer()
        binding.rvReviews.setGone()
    }

    private fun hideLoading() {
        binding.shimmerReview.setGone()
        binding.shimmerReview.stopShimmer()
        binding.rvReviews.setVisible()
    }

}
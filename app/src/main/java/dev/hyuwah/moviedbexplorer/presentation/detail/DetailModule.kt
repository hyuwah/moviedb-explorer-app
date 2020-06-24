package dev.hyuwah.moviedbexplorer.presentation.detail

import dev.hyuwah.moviedbexplorer.presentation.detail.mapper.FavoriteItemMapper
import dev.hyuwah.moviedbexplorer.presentation.detail.mapper.ReviewListMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    single { ReviewListMapper() }
    single { FavoriteItemMapper() }
    viewModel { DetailViewModel(get(), get(), get()) }
}
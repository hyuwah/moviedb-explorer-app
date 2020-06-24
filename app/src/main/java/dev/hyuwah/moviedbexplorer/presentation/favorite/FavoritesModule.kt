package dev.hyuwah.moviedbexplorer.presentation.favorite

import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.MovieItemMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    single { MovieItemMapper() }
    viewModel { FavoritesViewModel(get(), get()) }
}
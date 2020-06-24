package dev.hyuwah.moviedbexplorer.presentation.home

import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.MovieListMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single { MovieListMapper() }
    viewModel { HomeViewModel(get(), get()) }
}
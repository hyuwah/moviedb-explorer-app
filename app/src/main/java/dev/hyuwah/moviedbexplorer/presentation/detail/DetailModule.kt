package dev.hyuwah.moviedbexplorer.presentation.detail

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    single { ReviewListMapper() }
    viewModel { DetailViewModel(get(), get()) }
}
package dev.hyuwah.moviedbexplorer.core.di

import dev.hyuwah.moviedbexplorer.presentation.home.homeModule
import dev.hyuwah.moviedbexplorer.presentation.shared.mapper.MovieListMapper
import org.koin.dsl.module

val presentationModule = listOf(
    module {
        single { MovieListMapper() }
    },
    homeModule
)
package dev.hyuwah.moviedbexplorer.core.di

import dev.hyuwah.moviedbexplorer.presentation.detail.detailModule
import dev.hyuwah.moviedbexplorer.presentation.favorite.favoritesModule
import dev.hyuwah.moviedbexplorer.presentation.home.homeModule

val presentationModule = listOf(
    homeModule,
    detailModule,
    favoritesModule
)
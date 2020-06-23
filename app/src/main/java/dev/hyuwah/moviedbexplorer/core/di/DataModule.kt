package dev.hyuwah.moviedbexplorer.core.di

import dev.hyuwah.moviedbexplorer.data.local.persistenceModule
import dev.hyuwah.moviedbexplorer.data.remote.networkModule
import dev.hyuwah.moviedbexplorer.data.repository.repositoryModule

val dataModule = listOf(
    networkModule,
    persistenceModule,
    repositoryModule
)
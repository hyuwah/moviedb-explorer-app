package dev.hyuwah.moviedbexplorer.data.repository

import org.koin.dsl.module

val repositoryModule = module {

    single<MovieDbRepository> { MovieDbRepositoryImpl(get()) }

}
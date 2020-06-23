package dev.hyuwah.moviedbexplorer.core

import android.app.Application
import dev.hyuwah.moviedbexplorer.core.di.dataModule
import dev.hyuwah.moviedbexplorer.core.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieDbExplorerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieDbExplorerApp)
            modules(dataModule)
            modules(presentationModule)
        }
    }

}
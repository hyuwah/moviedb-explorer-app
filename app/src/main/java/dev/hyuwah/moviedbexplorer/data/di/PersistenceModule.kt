package dev.hyuwah.moviedbexplorer.data.di

import androidx.room.Room
import dev.hyuwah.moviedbexplorer.data.di.PersistenceConstant.DB_NAME
import dev.hyuwah.moviedbexplorer.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object PersistenceConstant {
    const val DB_NAME = "moviedbexplorer_db"
}

val persistenceModule = module {

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, DB_NAME).build() }

}
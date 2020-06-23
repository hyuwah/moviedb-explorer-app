package dev.hyuwah.moviedbexplorer.data.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import dev.hyuwah.moviedbexplorer.BuildConfig
import dev.hyuwah.moviedbexplorer.data.di.NetworkConstant.BASE_URL
import dev.hyuwah.moviedbexplorer.data.di.NetworkConstant.ChuckerInterceptor
import dev.hyuwah.moviedbexplorer.data.di.NetworkConstant.LogInterceptor
import dev.hyuwah.moviedbexplorer.data.remote.MovieServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConstant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    val ChuckerInterceptor = named("chuckerInterceptor")
    val LogInterceptor = named("logInterceptor")
}

val networkModule = module {

    // Interceptors
    single(ChuckerInterceptor) { ChuckerInterceptor(androidContext()) }
    single(LogInterceptor) { HttpLoggingInterceptor() }

    // OkHttpClients
    single {
        OkHttpClient.Builder()
            .addInterceptor {
                val oldReq = it.request()
                val newUrl = oldReq.url().newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()
                val newReq = oldReq.newBuilder().url(newUrl).build()
                it.proceed(newReq)
            }
            .addInterceptor(get(ChuckerInterceptor))
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(get(LogInterceptor))
            }
            .build()
    }

    // Service Apis
    single { createRestApiAdapter<MovieServiceApi>(get(), BASE_URL) }
}

inline fun <reified T> createRestApiAdapter(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}
package com.quraanali.trendingmovies.data.source.remote

import com.quraanali.trendingmovies.BuildConfig
import com.quraanali.trendingmovies.data.movies.MoviesLocalDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppInterceptor @Inject constructor(
    private val moviesLocalDataSource: MoviesLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newBuilder = original.newBuilder()

        val auth: String? = runBlocking {
            moviesLocalDataSource.getTmdbToken()
        }

        auth?.let {
            newBuilder
                .header("Authorization", "Bearer $it")
                .build()
        } ?: run {
            val accessToken: String = BuildConfig.TMDB_API_AUTH
            newBuilder
                .header("Authorization", "Bearer $accessToken")
                .build()

        }
        newBuilder.addHeader("Accept", "application/json")

        val request = newBuilder.build()
        return chain.proceed(request)
    }
}
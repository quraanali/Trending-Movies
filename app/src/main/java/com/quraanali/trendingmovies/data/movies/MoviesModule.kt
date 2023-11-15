package com.quraanali.trendingmovies.data.movies

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.quraanali.trendingmovies.data.source.remote.ApiEndPoints
import com.quraanali.trendingmovies.di.IoDispatcher
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {
    @Provides
    fun provideMoviesRemoteDataSource(
        moshi: Moshi,
        apiEndPoints: ApiEndPoints
    ): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(moshi, apiEndPoints)
    }

    @Provides
    fun provideMoviesLocalDataSource(
        dataStore: DataStore<Preferences>,
    ): MoviesLocalDataSource {
        return MoviesLocalDataSourceImpl(dataStore)
    }


    @Singleton
    @Provides
    fun provideMoviesDataSource(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        moviesLocalDataSource: MoviesLocalDataSource,
        moviesRemoteDataSource: MoviesRemoteDataSource,
    ): MoviesDataSource {
        return MoviesRepository(
            dispatcher,
            moviesLocalDataSource,
            moviesRemoteDataSource
        )
    }
}

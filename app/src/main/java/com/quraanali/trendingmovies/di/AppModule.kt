package com.quraanali.trendingmovies.di

import android.content.Context
import com.quraanali.trendingmovies.AppState
import com.quraanali.trendingmovies.AppStateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideAppState(@ApplicationContext context: Context): AppState {
        return AppStateImpl(context)
    }
}
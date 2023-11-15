package com.quraanali.trendingmovies.data.movies

import com.quraanali.trendingmovies.ui.screens.movies.MoviesUiState

interface MoviesLocalDataSource {
    suspend fun saveTmdbToken(token: String)
    suspend fun getTmdbToken(): String?

    suspend fun cashingMoviesData(movieUi: MoviesUiState)
    suspend fun getCashedMoviesData(): MoviesUiState?
}
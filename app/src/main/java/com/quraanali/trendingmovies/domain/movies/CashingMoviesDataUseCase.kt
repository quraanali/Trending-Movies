package com.quraanali.trendingmovies.domain.movies


import com.quraanali.trendingmovies.data.movies.MoviesDataSource
import com.quraanali.trendingmovies.ui.screens.movies.MoviesUiState
import javax.inject.Inject

class CashingMoviesDataUseCase @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
) {
    suspend operator fun invoke(
        moviesUiState: MoviesUiState
    ) {
        if (moviesUiState.movieList.isEmpty() && moviesUiState.genresList.isEmpty()) return
        moviesDataSource.cashingMoviesData(moviesUiState)
    }
}
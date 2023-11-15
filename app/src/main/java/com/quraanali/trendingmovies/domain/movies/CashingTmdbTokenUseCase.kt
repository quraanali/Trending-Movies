package com.quraanali.trendingmovies.domain.movies


import com.quraanali.trendingmovies.data.movies.MoviesDataSource
import javax.inject.Inject

class CashingTmdbTokenUseCase @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
) {
    suspend operator fun invoke(
        token: String? = null
    ) {
        if (token == null) return
        moviesDataSource.saveTmdbToken(token.trim())
    }
}
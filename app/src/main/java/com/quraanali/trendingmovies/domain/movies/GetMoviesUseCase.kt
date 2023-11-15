package com.quraanali.trendingmovies.domain.movies

import com.quraanali.trendingmovies.data.movies.MoviesDataSource
import com.quraanali.trendingmovies.data.movies.models.MoviesResponse
import com.quraanali.trendingmovies.data.source.remote.DefaultResponse
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
) {
    suspend operator fun invoke(
        page: Int,
        language: String? = null
    ): DefaultResponse<MoviesResponse> {
        return moviesDataSource.getMovies(page, language)
    }
}
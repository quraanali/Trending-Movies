package com.quraanali.trendingmovies.domain.movies

import com.quraanali.trendingmovies.data.movies.MoviesDataSource
import com.quraanali.trendingmovies.data.movies.models.GenresResponse
import com.quraanali.trendingmovies.data.source.remote.DefaultResponse
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
) {
    suspend operator fun invoke(
        language: String? = null
    ): DefaultResponse<GenresResponse> {
        return moviesDataSource.getGenres(language)
    }
}
package com.quraanali.trendingmovies.domain.movies

import com.quraanali.trendingmovies.data.movies.MoviesDataSource
import com.quraanali.trendingmovies.data.movies.models.MovieDetailsResponse
import com.quraanali.trendingmovies.data.source.remote.DefaultResponse
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
) {
    suspend operator fun invoke(
        id: Int,
        language: String? = null
    ): DefaultResponse<MovieDetailsResponse> {
        return moviesDataSource.getMovieDetails(id, language)
    }
}
package com.quraanali.trendingmovies.data.movies

import com.quraanali.trendingmovies.data.movies.models.GenresResponse
import com.quraanali.trendingmovies.data.movies.models.MovieDetailsResponse
import com.quraanali.trendingmovies.data.movies.models.MoviesResponse
import com.quraanali.trendingmovies.data.source.remote.DefaultResponse

interface MoviesRemoteDataSource {
    suspend fun getGenres(
        language: String?
    ): DefaultResponse<GenresResponse>


    suspend fun getMovies(
        page: Int,
        language: String?
    ): DefaultResponse<MoviesResponse>


    suspend fun getMovieDetails(
        id: Int,
        language: String?
    ): DefaultResponse<MovieDetailsResponse>
}
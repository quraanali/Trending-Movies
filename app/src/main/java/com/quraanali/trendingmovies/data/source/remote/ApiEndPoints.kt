package com.quraanali.trendingmovies.data.source.remote

import com.quraanali.trendingmovies.data.movies.models.GenresResponse
import com.quraanali.trendingmovies.data.movies.models.MovieDetailsResponse
import com.quraanali.trendingmovies.data.movies.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiEndPoints {

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("language") language: String? = "en"): Response<GenresResponse>

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("language") language: String? = "en",
        @Query("include_adult") includeAdult: Boolean? = false,
    ): Response<MoviesResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("language") language: String? = "en",
    ): Response<MovieDetailsResponse>
}
package com.quraanali.trendingmovies.data.movies

import com.quraanali.trendingmovies.data.movies.models.GenresResponse
import com.quraanali.trendingmovies.data.movies.models.MovieDetailsResponse
import com.quraanali.trendingmovies.data.movies.models.MoviesResponse
import com.quraanali.trendingmovies.data.source.remote.ApiEndPoints
import com.quraanali.trendingmovies.data.source.remote.DefaultResponse
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moshi: Moshi, private val apiEndPoints: ApiEndPoints
) : MoviesRemoteDataSource {

    override suspend fun getGenres(language: String?): DefaultResponse<GenresResponse> {
        return try {
            DefaultResponse.create(
                moshi, apiEndPoints.getGenres(
                    language
                )
            )
        } catch (e: Exception) {
            DefaultResponse.create(e)
        }
    }

    override suspend fun getMovies(page: Int, language: String?): DefaultResponse<MoviesResponse> {
        return try {
            DefaultResponse.create(
                moshi, apiEndPoints.getMovies(
                    page, language
                )
            )
        } catch (e: Exception) {
            DefaultResponse.create(e)
        }
    }

    override suspend fun getMovieDetails(
        id: Int, language: String?
    ): DefaultResponse<MovieDetailsResponse> {
        return try {
            DefaultResponse.create(
                moshi, apiEndPoints.getMovieDetails(
                    id, language
                )
            )
        } catch (e: Exception) {
            DefaultResponse.create(e)
        }
    }
}
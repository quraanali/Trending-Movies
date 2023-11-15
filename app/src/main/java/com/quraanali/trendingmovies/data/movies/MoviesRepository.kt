package com.quraanali.trendingmovies.data.movies

import com.quraanali.trendingmovies.data.movies.models.GenresResponse
import com.quraanali.trendingmovies.data.movies.models.MovieDetailsResponse
import com.quraanali.trendingmovies.data.movies.models.MoviesResponse
import com.quraanali.trendingmovies.data.source.remote.DefaultResponse
import com.quraanali.trendingmovies.ui.screens.movies.MoviesUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface MoviesDataSource : MoviesRemoteDataSource, MoviesLocalDataSource

@Singleton
class MoviesRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
) : MoviesDataSource {

    override suspend fun getGenres(language: String?): DefaultResponse<GenresResponse> {
        return withContext(dispatcher) { moviesRemoteDataSource.getGenres(language) }
    }

    override suspend fun getMovies(page: Int, language: String?): DefaultResponse<MoviesResponse> {
        return withContext(dispatcher) { moviesRemoteDataSource.getMovies(page, language) }
    }

    override suspend fun getMovieDetails(
        id: Int,
        language: String?
    ): DefaultResponse<MovieDetailsResponse> {
        return withContext(dispatcher) { moviesRemoteDataSource.getMovieDetails(id, language) }
    }

    override suspend fun saveTmdbToken(token: String) {
        return withContext(dispatcher) { moviesLocalDataSource.saveTmdbToken(token) }
    }

    override suspend fun getTmdbToken(): String? {
        return withContext(dispatcher) { moviesLocalDataSource.getTmdbToken() }
    }

    override suspend fun cashingMoviesData(movieUi: MoviesUiState) {
        return withContext(dispatcher) { moviesLocalDataSource.cashingMoviesData(movieUi) }
    }

    override suspend fun getCashedMoviesData(): MoviesUiState? {
        return withContext(dispatcher) { moviesLocalDataSource.getCashedMoviesData() }
    }
}
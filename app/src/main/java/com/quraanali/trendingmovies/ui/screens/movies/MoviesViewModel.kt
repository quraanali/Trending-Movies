package com.quraanali.trendingmovies.ui.screens.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quraanali.trendingmovies.AppState
import com.quraanali.trendingmovies.data.source.remote.DefaultResponse
import com.quraanali.trendingmovies.domain.movies.CashingMoviesDataUseCase
import com.quraanali.trendingmovies.domain.movies.CashingTmdbTokenUseCase
import com.quraanali.trendingmovies.domain.movies.GetCashedMoviesDataUseCase
import com.quraanali.trendingmovies.domain.movies.GetGenresUseCase
import com.quraanali.trendingmovies.domain.movies.GetMoviesUseCase
import com.quraanali.trendingmovies.utils.isInternetConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val cashingTmdbTokenUseCase: CashingTmdbTokenUseCase,
    private val cashingMoviesDataUseCase: CashingMoviesDataUseCase,
    private val getCashedMoviesDataUseCase: GetCashedMoviesDataUseCase,
    appState: AppState
) : AppState by appState, ViewModel() {
    private val _moviesUiState = MutableStateFlow(MoviesUiState())
    val moviesUiState = _moviesUiState.asStateFlow()
    private var cashedMoviesList = mutableListOf<MoviesUiState.MovieUi>()

    private var currentPage = 1
    private var isLastPage = false

    init {
        if (getApplication().applicationContext.isInternetConnected()) {
            getGenres()
            getMovies()
        } else {
            getCashedData()
        }
    }

    private fun getCashedData() {
        viewModelScope.launch {
            val ui = getCashedMoviesDataUseCase()
            if (ui != null) {
                _moviesUiState.update { ui }
            }
        }
    }

    private fun getGenres() {
        setLoadingState(true)
        viewModelScope.launch {
            when (val result = getGenresUseCase()) {
                is DefaultResponse.Success -> {
                    val genresUi = mutableListOf<MoviesUiState.GenresUi>()
                    result.body.genres.forEach {
                        genresUi.add(MoviesUiState.GenresUi(name = it.name, id = it.id))
                    }
                    _moviesUiState.update {
                        it.copy(
                            genresList = genresUi
                        )
                    }
                    setLoadingState(false)
                    cashing()
                }

                is DefaultResponse.Fail -> {
                    setLoadingState(false)
                }
            }
        }
    }


    fun getMovies() {
        // to prevent loadMore during search
        if (cashedMoviesList.isNotEmpty()) return


        if (currentPage != 1) {
            Log.d("getMoooovies", "LoadMore")
            _moviesUiState.update {
                it.copy(
                    isLoadMore = true,
                )
            }
        } else {
            Log.d("getMoooovies", "firstTime")
            setLoadingState(true)
        }


        viewModelScope.launch {
            when (val result = getMoviesUseCase(currentPage)) {
                is DefaultResponse.Success -> {
                    isLastPage = currentPage >= result.body.totalPages
                    val movieUiList = mutableListOf<MoviesUiState.MovieUi>()
                    movieUiList.addAll(_moviesUiState.value.movieList)
                    result.body.results.forEach { movie ->
                        movieUiList.add(
                            MoviesUiState.MovieUi(
                                thumbUrl = "https://image.tmdb.org/t/p/original/" + movie.posterPath,
                                name = movie.title,
                                year = movie.releaseDate,
                                id = movie.id
                            )
                        )

                    }

                    _moviesUiState.update {
                        it.copy(
                            isLoadMore = false, movieList = movieUiList
                        )
                    }

                    currentPage++

                    setLoadingState(false)
                    cashing()
                }

                is DefaultResponse.Fail -> {
                    _moviesUiState.update {
                        it.copy(
                            isLoadMore = false,
                        )
                    }
                    setLoadingState(false)
                }
            }
        }
    }


    fun setAuthToken(token: String) {
        viewModelScope.launch { cashingTmdbTokenUseCase(token) }
    }


    fun searchOf(query: String) {
        Log.d("searchOf", query)
        if (cashedMoviesList.isEmpty()) cashedMoviesList = _moviesUiState.value.movieList


        // this condition to reset movies list again after complete search
        if (query.isEmpty() && cashedMoviesList.isNotEmpty()) {
            _moviesUiState.update {
                it.copy(
                    movieList = cashedMoviesList
                )
            }
            cashedMoviesList = mutableListOf()
            return
        }

        val resultList = mutableListOf<MoviesUiState.MovieUi>()

        cashedMoviesList.forEach {
            if (it.name.contains(query, true)) resultList.add(it)
        }

        if (resultList.isNotEmpty()) {
            _moviesUiState.update {
                it.copy(
                    movieList = resultList
                )
            }
        } else {
            // to show empty list in case no results found
            _moviesUiState.update {
                it.copy(
                    movieList = mutableListOf()
                )
            }
        }
    }

    private fun cashing() {
        viewModelScope.launch {
            cashingMoviesDataUseCase(_moviesUiState.value)
        }
    }
}
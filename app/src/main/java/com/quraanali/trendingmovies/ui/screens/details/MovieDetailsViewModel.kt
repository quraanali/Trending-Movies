package com.quraanali.trendingmovies.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quraanali.trendingmovies.AppState
import com.quraanali.trendingmovies.data.source.remote.DefaultResponse
import com.quraanali.trendingmovies.domain.movies.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MovieDetailsViewModel @Inject constructor(
    appState: AppState, private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : AppState by appState, ViewModel() {


    private val _movieDetailsUiState = MutableStateFlow(MovieDetailsUiState())
    val movieDetailsUiState = _movieDetailsUiState.asStateFlow()

    fun setMovieId(id: Int) {
        if (_movieDetailsUiState.value.id != null) return

        setLoadingState(true)
        _movieDetailsUiState.update {
            it.copy(
                id = id
            )
        }


        viewModelScope.launch {
            when (val result = getMovieDetailsUseCase(id)) {
                is DefaultResponse.Success -> {
                    val movie = result.body
                    movie.run {
                        _movieDetailsUiState.update {
                            it.copy(
                                background = "https://image.tmdb.org/t/p/original/$backdropPath",
                                poster = "https://image.tmdb.org/t/p/original/$posterPath",
                                title = title,
                                genres = genres.joinToString { genre -> genre.name },
                                overview = overview,
                                releaseDate = releaseDate,
                                homepage = homepage,
                                budget = "$budget $",
                                revenue = "$revenue $",
                                spokenLanguage = spokenLanguages.joinToString { lang -> lang.name },
                                status = status,
                                runtime = "$runtime $",
                            )
                        }
                    }
                    setLoadingState(false)
                }

                is DefaultResponse.Fail -> {
                    setLoadingState(false)
                }

            }
        }

    }
}
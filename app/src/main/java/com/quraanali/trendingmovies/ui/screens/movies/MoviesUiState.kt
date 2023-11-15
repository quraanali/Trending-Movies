package com.quraanali.trendingmovies.ui.screens.movies


data class MoviesUiState(
    val isLoadMore: Boolean = false,
    val genresList: MutableList<GenresUi> = mutableListOf(),
    val movieList: MutableList<MovieUi> = mutableListOf(),
) {
    data class GenresUi(val name: String = "", val id: Int = -1)
    data class MovieUi(
        val thumbUrl: String,
        val name: String = "",
        val year: String = "",
        val id: Int = -1
    )
}


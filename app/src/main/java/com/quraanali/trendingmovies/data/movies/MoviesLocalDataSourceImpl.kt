package com.quraanali.trendingmovies.data.movies

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mazenrashed.moshikotlinextensions.deserialize
import com.mazenrashed.moshikotlinextensions.serialize
import com.quraanali.trendingmovies.ui.screens.movies.MoviesUiState
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : MoviesLocalDataSource {
    private val tmdbToken = stringPreferencesKey(TMDB_TOKEN)
    private val moviesData = stringPreferencesKey(MOVIES_DATA)

    override suspend fun saveTmdbToken(token: String) {
        dataStore.edit { settings ->
            settings[tmdbToken] = token
        }
    }

    override suspend fun getTmdbToken(): String? {
        val token = dataStore.data.map { preferences ->
            preferences[tmdbToken]
        }.firstOrNull()
        return token
    }

    override suspend fun cashingMoviesData(movieUi: MoviesUiState) {
        dataStore.edit { settings ->
            settings[moviesData] = movieUi.serialize()
        }
    }

    override suspend fun getCashedMoviesData(): MoviesUiState? {
        val data = dataStore.data.map { preferences ->
            preferences[moviesData]
        }.firstOrNull()
        return data?.deserialize<MoviesUiState>()
    }

    companion object {
        private const val TMDB_TOKEN = "tmdb_token"
        private const val MOVIES_DATA = "movies_data"
    }
}
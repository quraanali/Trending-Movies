package com.quraanali.trendingmovies.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quraanali.trendingmovies.domain.common.GetTmdbApiKeyFromRemoteConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTmdbApiKeyFromRemoteConfigUseCase: GetTmdbApiKeyFromRemoteConfigUseCase
) : ViewModel() {
    private val _splashUiState = MutableStateFlow(SplashUiState())
    val splashUiState = _splashUiState.asStateFlow()
    fun checkAuthentication() {
        viewModelScope.launch {
            getTmdbApiKeyFromRemoteConfigUseCase().collectLatest { token ->
                delay(2000)
                _splashUiState.update { it.copy(authToken = token) }
            }
        }
    }
}
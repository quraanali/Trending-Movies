package com.quraanali.trendingmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quraanali.trendingmovies.domain.common.GetTmdbApiKeyFromRemoteConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTmdbApiKeyFromRemoteConfigUseCase: GetTmdbApiKeyFromRemoteConfigUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getTmdbApiKeyFromRemoteConfigUseCase().collectLatest {
            }
        }
    }

}
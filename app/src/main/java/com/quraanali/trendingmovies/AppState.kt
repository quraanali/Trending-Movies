package com.quraanali.trendingmovies

import android.content.Context
import com.quraanali.trendingmovies.utils.toast
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

interface AppState {
    fun setLoadingState(isLoading: Boolean)
    fun setToastMsg(text: String)
    fun getApplication(): Context
    val loadingState: SharedFlow<Boolean>

}

class AppStateImpl @Inject constructor(private val application: Context) :
    AppState {
    private val _loadingState = MutableSharedFlow<Boolean>(replay = 1)
    override val loadingState: SharedFlow<Boolean>
        get() = _loadingState

    override fun setLoadingState(isLoading: Boolean) {
        _loadingState.tryEmit(isLoading)
    }

    override fun setToastMsg(text: String) {
        text.toast(getApplication())
    }


    override fun getApplication(): Context {
        return application
    }
}
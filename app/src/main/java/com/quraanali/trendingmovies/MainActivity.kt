package com.quraanali.trendingmovies

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.quraanali.trendingmovies.ui.components.InitNavigation
import com.quraanali.trendingmovies.ui.components.ProgressLoading
import com.quraanali.trendingmovies.ui.theme.TrendingMoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var appStat: AppState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrendingMoviesTheme {
                var isLoading by remember { mutableStateOf(false) }

                Box(modifier = Modifier.fillMaxSize()) {
                    Scaffold(content = { innerPadding ->
                        InitNavigation(modifier = Modifier, innerPadding)
                    })
                    LaunchedEffect(true) {
                        appStat.loadingState.collect() {
                            isLoading = it
                        }
                    }
                    ProgressLoading(isShow = isLoading)
                }
            }
        }
    }

}

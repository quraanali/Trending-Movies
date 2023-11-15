package com.quraanali.trendingmovies.ui.screens.splash

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.quraanali.trendingmovies.R
import com.quraanali.trendingmovies.ui.screens.Routes
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashView(navController: NavHostController) {
    val viewModel = hiltViewModel<SplashViewModel>()

    LaunchedEffect(key1 = true) {
        viewModel.splashUiState.collectLatest {
            if (it.authToken != null) {
                navController.navigate(route = Routes.MOVIES.type + "/" + it.authToken + " /") {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    }

    viewModel.checkAuthentication()

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context).components {
        if (SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.movies).apply(block = {

            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )


}

@Preview
@Composable
fun SplashPreview() {
    val navController = rememberNavController()
    SplashView(navController = navController)

}
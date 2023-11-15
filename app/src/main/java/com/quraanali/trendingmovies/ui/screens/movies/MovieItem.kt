package com.quraanali.trendingmovies.ui.screens.movies

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.quraanali.trendingmovies.R
import com.quraanali.trendingmovies.ui.components.AppText
import com.quraanali.trendingmovies.ui.components.FontFamilyEnum
import com.quraanali.trendingmovies.ui.theme.PrimaryColorDisabled
import com.quraanali.trendingmovies.ui.theme.dp200
import com.quraanali.trendingmovies.ui.theme.dp280
import com.quraanali.trendingmovies.ui.theme.dp4
import com.quraanali.trendingmovies.ui.theme.dp8
import com.quraanali.trendingmovies.ui.theme.sp16


@Composable
fun MovieItem(
    movie: MoviesUiState.MovieUi, onClick: (movie: MoviesUiState.MovieUi) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(dp4),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryColorDisabled,
        ),
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(dp8)
            .height(dp280)
            .clickable {
                onClick(movie)
            }) {
            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context).components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }.build()
            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = R.drawable.loading).apply(block = {

                }).build(), imageLoader = imageLoader
            )
            AsyncImage(
                model = movie.thumbUrl,
                placeholder = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dp200)
            )

            AppText(
                text = movie.name,
                textSize = sp16,
                fontFamily = FontFamilyEnum.BOLD,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dp4),
                maxLine = 2
            )
            Spacer(modifier = Modifier.weight(1f))
            AppText(
                text = movie.year,
                fontFamily = FontFamilyEnum.MEDIUM,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dp4)
            )
        }
    }
}

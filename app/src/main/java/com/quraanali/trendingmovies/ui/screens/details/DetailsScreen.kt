package com.quraanali.trendingmovies.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.quraanali.trendingmovies.R
import com.quraanali.trendingmovies.ui.components.AppText
import com.quraanali.trendingmovies.ui.components.AppToolBar
import com.quraanali.trendingmovies.ui.components.FontFamilyEnum
import com.quraanali.trendingmovies.ui.components.TextTitleValue
import com.quraanali.trendingmovies.ui.theme.dp120
import com.quraanali.trendingmovies.ui.theme.dp16
import com.quraanali.trendingmovies.ui.theme.dp20
import com.quraanali.trendingmovies.ui.theme.dp5
import com.quraanali.trendingmovies.ui.theme.dp80
import com.quraanali.trendingmovies.ui.theme.sp18
import com.quraanali.trendingmovies.utils.openLink
import com.quraanali.trendingmovies.utils.share


@Composable
fun MovieDetails(navController: NavHostController, id: Int) {
    val viewModel = hiltViewModel<MovieDetailsViewModel>()
    viewModel.setMovieId(id)

    val uiState = viewModel.movieDetailsUiState.collectAsState().value

    val scrollState = rememberScrollState()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        ) {
            AsyncImage(
                model = uiState.background,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
            AppToolBar(onActionClicked = {
                uiState.homepage.share(context)
            }, onBackClicked = {
                navController.popBackStack()
            })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dp20, start = dp16)
        ) {
            AsyncImage(
                model = uiState.poster,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(dp120)
                    .width(dp80)
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .height(dp120)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                AppText(
                    text = uiState.title,
                    fontFamily = FontFamilyEnum.BOLD, textSize = sp18,
                    modifier = Modifier.padding(start = dp16),
                )

                AppText(
                    text = uiState.genres,
                    modifier = Modifier.padding(start = dp16),
                )

                AppText(
                    text = uiState.releaseDate,
                    modifier = Modifier.padding(start = dp16),
                )

            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(state = scrollState)
        ) {

            Text(
                text = uiState.overview,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(dp16)
            )
        }

        Column(
            modifier = Modifier
                .padding(dp16),
            verticalArrangement = Arrangement.Bottom
        ) {
            TextTitleValue(pair = Pair(
                stringResource(id = R.string.homePage), uiState.homepage
            ), true, onClick = {
                it.openLink(context)
            })

            Spacer(modifier = Modifier.height(dp5))

            TextTitleValue(
                pair = Pair(
                    stringResource(id = R.string.language), uiState.spokenLanguage
                )
            )
            Spacer(modifier = Modifier.height(dp5))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextTitleValue(
                    pair = Pair(
                        stringResource(id = R.string.status), uiState.status
                    )
                )
                TextTitleValue(
                    pair = Pair(
                        stringResource(id = R.string.runtime), uiState.runtime
                    )
                )

            }
            Spacer(modifier = Modifier.height(dp5))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextTitleValue(
                    pair = Pair(
                        stringResource(id = R.string.budget), uiState.budget
                    )
                )
                TextTitleValue(
                    pair = Pair(
                        stringResource(id = R.string.revenue), uiState.revenue
                    )
                )

            }
        }
    }

}


@Preview
@Composable
fun DetailsPreview() {
    MovieDetails(navController = rememberNavController(), 0)

}
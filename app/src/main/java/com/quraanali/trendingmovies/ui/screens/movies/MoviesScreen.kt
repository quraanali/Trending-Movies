package com.quraanali.trendingmovies.ui.screens.movies

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.quraanali.trendingmovies.R
import com.quraanali.trendingmovies.ui.components.AppText
import com.quraanali.trendingmovies.ui.components.FontFamilyEnum
import com.quraanali.trendingmovies.ui.components.SearchTextField
import com.quraanali.trendingmovies.ui.screens.Routes
import com.quraanali.trendingmovies.ui.theme.Gold
import com.quraanali.trendingmovies.ui.theme.Red
import com.quraanali.trendingmovies.ui.theme.dp10
import com.quraanali.trendingmovies.ui.theme.dp12
import com.quraanali.trendingmovies.ui.theme.dp16
import com.quraanali.trendingmovies.ui.theme.dp2
import com.quraanali.trendingmovies.ui.theme.dp40
import com.quraanali.trendingmovies.ui.theme.dp8
import com.quraanali.trendingmovies.ui.theme.sp26
import com.quraanali.trendingmovies.utils.OnBottomReached

@Composable
fun HomeMovies(navController: NavHostController, token: String) {

    val viewModel = hiltViewModel<MoviesViewModel>()

    viewModel.setAuthToken(token)


    val uiState by viewModel.moviesUiState.collectAsState()
    var tabs = listOf<MoviesUiState.GenresUi>()

    if (uiState.genresList.isNotEmpty()) {
        tabs = uiState.genresList
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dp12)
        ) {
            SearchTextField(onValueChange = { viewModel.searchOf(it) }, onSearchIconClick = {})

            AppText(
                text = stringResource(id = R.string.watch_new_movies),
                textColor = Gold,
                textSize = sp26,
                modifier = Modifier.padding(horizontal = dp10, vertical = dp12),
                fontFamily = FontFamilyEnum.BOLD
            )

            var tabIndex by remember { mutableIntStateOf(0) }

            LazyRow(horizontalArrangement = Arrangement.spacedBy(dp8),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = dp16, end = dp16),
                content = {
                    itemsIndexed(tabs.toList()) { index, item ->
                        val border = BorderStroke(dp2, Gold)
                        val modifier = if (index == tabIndex) Modifier
                            .border(
                                border, shape = MaterialTheme.shapes.large
                            )
                            .background(Color.Transparent)
                        else Modifier
                            .clip(RoundedCornerShape(20))
                            .background(Gold)
                        Tab(
                            modifier = modifier,
                            text = { Text(item.name) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            selectedContentColor = Color.Black,
                            unselectedContentColor = Color.White,
                        )
                    }
                })

            Spacer(modifier = Modifier.height(dp12))

            // Create a LazyListGridState to listen for scroll position
            val lazyListState = rememberLazyGridState()

            LazyVerticalGrid(state = lazyListState,
                modifier = Modifier,
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = dp12, end = dp12, bottom = dp16
                ),
                content = {
                    items(uiState.movieList) { movieUi ->
                        MovieItem(movieUi, onClick = {
                            navController.navigate(route = Routes.MOVIE_DETAILS.type + "/" + movieUi.id + "/") {

                            }
                        })
                    }
                })

            lazyListState.OnBottomReached {
                // to prevent load more in the first launch
                if (uiState.movieList.isNotEmpty()) viewModel.getMovies()
            }

        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dp16), contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoadMore) {

                Box(
                    modifier = Modifier
                        .height(dp40)
                        .fillMaxWidth()
                        .background(Color.White)
                        .align(Alignment.BottomCenter)
                ) {
                    CircularProgressIndicator(
                        color = Red,
                        modifier = Modifier
                            .size(dp40)
                            .align(Alignment.Center),
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeMoviesPreview() {
    HomeMovies(rememberNavController(), "token")
}
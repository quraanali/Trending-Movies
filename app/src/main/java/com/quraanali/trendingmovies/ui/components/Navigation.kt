package com.quraanali.trendingmovies.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.quraanali.trendingmovies.ui.screens.Routes
import com.quraanali.trendingmovies.ui.screens.details.MovieDetails
import com.quraanali.trendingmovies.ui.screens.movies.HomeMovies
import com.quraanali.trendingmovies.ui.screens.splash.SplashView

@Composable
fun InitNavigation(modifier: Modifier, innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier.statusBarsPadding(),
        navController = navController, startDestination = Routes.SPLASH.type
    ) {
        composable(Routes.SPLASH.type) { SplashView(navController) }

        composable(Routes.MOVIES.type + "/{token}/",
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            HomeMovies(navController, backStackEntry.arguments?.getString("token") ?: "")
        }

        composable(Routes.MOVIE_DETAILS.type + "/{id}/",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )) { backStackEntry ->
            MovieDetails(navController, backStackEntry.arguments?.getInt("id") ?: 0)
        }
    }
}
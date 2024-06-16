package com.example.movies.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movies.ui.screens.DetailsScreen
import com.example.movies.ui.screens.FavoriteScreen
import com.example.movies.ui.screens.HomeScreen
import com.example.movies.ui.screens.SettingsScreen
import com.example.movies.ui.screens.WatchLaterScreen
import com.example.movies.ui.viewmodels.DetailsViewModel
import com.example.movies.ui.viewmodels.HomeViewModel

@Composable
fun BottomNavGraph(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    detailsViewModel: DetailsViewModel
) {
    NavHost(
        navController = navHostController, startDestination = BottomScreens.Home.route
    ) {
        composable(route = BottomScreens.Home.route) {
            HomeScreen(
                homeViewModel,
                navHostController
            )
        }
        composable(route = BottomScreens.Favorites.route) { FavoriteScreen() }
        composable(route = BottomScreens.WatchLater.route) { WatchLaterScreen() }
        composable(route = BottomScreens.Settings.route) { SettingsScreen() }
        composable(route = BottomScreens.Details.route) { DetailsScreen(detailsViewModel) }
    }
}
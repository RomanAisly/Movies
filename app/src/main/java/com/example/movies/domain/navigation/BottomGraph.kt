package com.example.movies.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movies.ui.screens.FavoriteScreen
import com.example.movies.ui.screens.HomeScreen
import com.example.movies.ui.screens.SettingsScreen
import com.example.movies.ui.screens.SplashScreen
import com.example.movies.ui.screens.WatchLaterScreen

@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    var splash by remember {
        mutableStateOf(false)
    }
    NavHost(
        navController = navHostController, startDestination = (if (splash == false) {
            BottomScreens.Splash.route
        } else {
            BottomScreens.Home.route
        }).toString()
    ) {
        composable(route = BottomScreens.Splash.route) { SplashScreen(navHostController) }
        composable(route = BottomScreens.Home.route) {
            HomeScreen()
            splash = true
        }
        composable(route = BottomScreens.Favorites.route) { FavoriteScreen() }
        composable(route = BottomScreens.WatchLater.route) { WatchLaterScreen() }
        composable(route = BottomScreens.Settings.route) { SettingsScreen() }
    }
}
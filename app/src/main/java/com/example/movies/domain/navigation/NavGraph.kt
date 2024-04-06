package com.example.movies.domain.navigation

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.ui.screens.FavoriteScreen
import com.example.movies.ui.screens.HomeScreen
import com.example.movies.ui.screens.SettingsScreen
import com.example.movies.ui.screens.WatchLaterScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottomItems.HOME,
    ) {
        composable(BottomItems.HOME) {
            HomeScreen()
        }
        composable(BottomItems.FAVORITE) {
            FavoriteScreen()
        }
        composable(BottomItems.WATCH_LATER) {
            WatchLaterScreen()
        }
        composable(BottomItems.SETTINGS) {
            SettingsScreen()
        }
    }
}
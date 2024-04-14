package com.example.movies.domain.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.ui.screens.FavoriteScreen
import com.example.movies.ui.screens.HomeScreenContent
import com.example.movies.ui.screens.Search
import com.example.movies.ui.screens.SettingsScreen
import com.example.movies.ui.screens.WatchLaterScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) },
        topBar = { Search() }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomItems.HomeBottomNav.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = BottomItems.HomeBottomNav.route) { HomeScreenContent() }
            composable(route = BottomItems.FavoriteBottomNav.route) { FavoriteScreen() }
            composable(route = BottomItems.WatchLaterBottomNav.route) { WatchLaterScreen() }
            composable(route = BottomItems.SettingsBottomNav.route) { SettingsScreen() }
        }

    }

}

@Preview(
    showBackground = true,
)
@Composable
fun MainPreview() {
    NavGraph()
}
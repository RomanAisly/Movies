package com.example.movies.domain.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreens(val title: String, val icon: ImageVector, val route: String) {
    data object Home : BottomScreens("Home", Icons.Default.Home, "home")
    data object Favorites : BottomScreens("Favorites", Icons.Default.Favorite, "favorites")
    data object WatchLater : BottomScreens("Watch Later", Icons.Default.WatchLater, "watch later")
    data object Settings : BottomScreens("Settings", Icons.Default.Settings, "settings")
}
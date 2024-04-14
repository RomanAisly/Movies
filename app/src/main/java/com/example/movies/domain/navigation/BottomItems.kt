package com.example.movies.domain.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movies.R

sealed class BottomItems(@StringRes val title: Int, val icon: ImageVector, val route: String) {

    data object HomeBottomNav :
        BottomItems(R.string.bottom_nav_item_home, Icons.Default.Home, "home")

    data object FavoriteBottomNav :
        BottomItems(R.string.bottom_nav_item_favorites, Icons.Default.Favorite, "favorites")

    data object WatchLaterBottomNav :
        BottomItems(R.string.bottom_nav_item_watch_later, Icons.Default.WatchLater, "watch later")

    data object SettingsBottomNav :
        BottomItems(R.string.bottom_nav_item_settings, Icons.Default.Settings, "settings")

}
package com.example.movies.domain.navigation

import com.example.movies.R

sealed class BottomItems(val title: String, val iconId: Int, val route: String) {

    data object HomeBottomNav : BottomItems("Home", R.drawable.icon_home, HOME )
    data object FavoriteBottomNav : BottomItems("Favorite", R.drawable.icon_favorite, FAVORITE )
    data object WatchLaterBottomNav : BottomItems("Watch Later", R.drawable.icon_watch_later, WATCH_LATER )
    data object SettingsBottomNav : BottomItems("Settings", R.drawable.icon_settings, SETTINGS )

    companion object{
        const val HOME = "home_screen"
        const val FAVORITE = "favorite_screen"
        const val WATCH_LATER = "watch_later_screen"
        const val SETTINGS = "settings_screen"
    }
}
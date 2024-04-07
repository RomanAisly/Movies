package com.example.movies.domain.navigation

import androidx.annotation.StringRes
import com.example.movies.R

sealed class BottomItems(@StringRes val title: Int, val iconId: Int, val route: String) {

    data object HomeBottomNav : BottomItems(R.string.bottom_nav_item_home, R.drawable.icon_home, HOME )
    data object FavoriteBottomNav : BottomItems(R.string.bottom_nav_item_favorites, R.drawable.icon_favorite, FAVORITE )
    data object WatchLaterBottomNav : BottomItems(R.string.bottom_nav_item_watch_later, R.drawable.icon_watch_later, WATCH_LATER )
    data object SettingsBottomNav : BottomItems(R.string.bottom_nav_item_settings, R.drawable.icon_settings, SETTINGS )

    companion object{
        const val HOME = "home_screen"
        const val FAVORITE = "favorite_screen"
        const val WATCH_LATER = "watch_later_screen"
        const val SETTINGS = "settings_screen"
    }


}
package com.example.movies.domain.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movies.R
import com.example.movies.ui.theme.colorForSearchBarBackground

@Composable
fun BottomNavBar(navController: NavController) {
    val navList = listOf(
        BottomItems.HomeBottomNav,
        BottomItems.FavoriteBottomNav,
        BottomItems.WatchLaterBottomNav,
        BottomItems.SettingsBottomNav
    )
    BottomAppBar(containerColor = colorForSearchBarBackground) {
        val backStack by navController.currentBackStackEntryAsState()
        val currentRoute = backStack?.destination?.route
        navList.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = { navController.navigate(navItem.route) },
                icon = {
                    Icon(
                        painterResource(id = navItem.iconId), contentDescription = stringResource(
                            R.string.cont_desc_navigation_item
                        )
                    )
                },
                label = { Text(text = navItem.title, fontSize = 9.sp) }
            )
        }
    }
}
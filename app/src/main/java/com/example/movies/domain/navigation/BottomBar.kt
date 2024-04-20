package com.example.movies.domain.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movies.R


@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomScreens.Home,
        BottomScreens.Favorites,
        BottomScreens.WatchLater,
        BottomScreens.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route



    BottomAppBar {
        screens.forEach { screenItem ->
            NavigationBarItem(
                selected = currentRoute == screenItem.route,
                onClick = {
                    navController.navigate(screenItem.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screenItem.icon,
                        contentDescription = stringResource(id = R.string.cont_desc_navigation_item)
                    )
                },
                label = { Text(text = screenItem.title) })
        }

    }
}
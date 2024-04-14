package com.example.movies.domain.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movies.R

@Composable
fun BottomNavBar(navController: NavController) {
    val screensList = listOf(
        BottomItems.HomeBottomNav,
        BottomItems.FavoriteBottomNav,
        BottomItems.WatchLaterBottomNav,
        BottomItems.SettingsBottomNav
    )


    BottomAppBar(containerColor = BottomAppBarDefaults.bottomAppBarFabColor) {
        val backStack by navController.currentBackStackEntryAsState()
        val currentRoute = backStack?.destination?.route
        screensList.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = LocalContentColor.current.copy(alpha = 0.4f),
                    selectedTextColor = Color.White,
                    unselectedTextColor = LocalContentColor.current.copy(alpha = 0.4f)
                ),
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon, contentDescription = stringResource(
                            R.string.cont_desc_navigation_item
                        )
                    )
                },
                label = {
                    Text(
                        text = LocalContext.current.getString(navItem.title),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                }
            )
        }
    }
}

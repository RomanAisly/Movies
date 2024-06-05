package com.example.movies.domain.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movies.R
import com.example.movies.ui.theme.backForBottomBar

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomScreens.Home,
        BottomScreens.Favorites,
        BottomScreens.WatchLater,
        BottomScreens.Settings
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar(
        containerColor = backForBottomBar,
    ) {
        screens.forEach { screenItem ->
            NavigationBarItem(
                selected = currentRoute == screenItem.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = LocalContentColor.current.copy(alpha = 0.5f),
                    selectedTextColor = Color.White,
                    unselectedTextColor = LocalContentColor.current.copy(alpha = 0.5f)
                ),
                onClick = {
                    navHostController.navigate(screenItem.route) {
                        popUpTo(navHostController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screenItem.icon,
                        contentDescription = stringResource(id = R.string.cont_desc_navigation_item)
                    )
                },
                label = {
                    Text(
                        text = LocalContext.current.getString(screenItem.title),
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                })
        }
    }
}
package com.example.movies.domain.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomScreens.Home,
        BottomScreens.Favorites,
        BottomScreens.WatchLater,
        BottomScreens.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route



    BottomAppBar(
        containerColor = BottomAppBarDefaults.containerColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        screens.forEach { screenItem ->
            NavigationBarItem(
                selected = currentRoute == screenItem.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = LocalContentColor.current.copy(alpha = 0.4f),
                    selectedTextColor = Color.White,
                    unselectedTextColor = LocalContentColor.current.copy(alpha = 0.4f)
                ),
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
                label = {
                    Text(
                        text = LocalContext.current.getString(screenItem.title),
                        fontSize = 10.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    )
                })
        }

    }
}
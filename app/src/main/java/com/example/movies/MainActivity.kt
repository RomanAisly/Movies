package com.example.movies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.movies.domain.navigation.BottomBar
import com.example.movies.domain.navigation.BottomNavGraph
import com.example.movies.ui.theme.MoviesTheme
import com.example.movies.ui.viewmodels.DetailsViewModel
import com.example.movies.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navHostController = navHostController) },
        content = {
            BottomNavGraph(
                navHostController = navHostController,
                homeViewModel,
                detailsViewModel
            )
        })
}
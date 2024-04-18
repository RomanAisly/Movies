package com.example.movies

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.data.remote.FilmsRepositoryImpl
import com.example.movies.data.remote.RetrofitInstance
import com.example.movies.domain.navigation.NavGraph
import com.example.movies.ui.screens.HomeScreenContent
import com.example.movies.ui.theme.MoviesTheme
import com.example.movies.ui.theme.gradForBack
import com.example.movies.ui.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.flow.collectLatest


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeScreenViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeScreenViewModel(FilmsRepositoryImpl(RetrofitInstance.moviesApi))
                        as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                NavGraph()
                val filmsList = viewModel.films.collectAsState().value
                val context = LocalContext.current

                LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                    viewModel.showErrorToastChannel.collectLatest { showOrNot ->
                        if (showOrNot) {
                            Toast.makeText(
                                context,
                                getString(R.string.toast_connection_has_failed), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                if (filmsList.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .background(gradForBack),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        userScrollEnabled = true
                    ) {
                        items(filmsList.size) { index ->
                            HomeScreenContent(filmsList[index])
                        }
                    }

                }
            }
        }
    }
}
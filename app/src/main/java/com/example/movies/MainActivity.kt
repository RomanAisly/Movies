package com.example.movies

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies.data.remote.FilmsRepositoryImpl
import com.example.movies.data.remote.MovieApi
import com.example.movies.data.remote.ResultDTO
import com.example.movies.data.remote.RetrofitInstance
import com.example.movies.domain.navigation.NavGraph
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
                            .wrapContentHeight()
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


@Composable
fun HomeScreenContent(films: ResultDTO) {

    Column(modifier = Modifier.clip(shape = RoundedCornerShape(15.dp))) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(MovieApi.IMAGE_URL)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.cont_desc_movie_post),
            placeholder = painterResource(id = R.drawable.placeholder)
        )

        Text(
            text = films.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
    }
}

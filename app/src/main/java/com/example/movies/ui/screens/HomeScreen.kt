package com.example.movies.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.data.remote.ResultDTO
import com.example.movies.domain.di.AppModule
import com.example.movies.ui.theme.gradForBack
import com.example.movies.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val allFilms = viewModel.allFilms.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.showErrorToast) {
        viewModel.showErrorToast.collectLatest { toast ->
            if (toast) {
                Toast.makeText(
                    context,
                    getString(context, R.string.toast_connection_has_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    if (allFilms.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradForBack),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Search()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradForBack),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = true,
                flingBehavior = ScrollableDefaults.flingBehavior()
            ) {
                items(allFilms) { films ->
                    FilmItem(films)
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    var searchText by remember {
        mutableStateOf("")
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradForBack)
            .padding(start = 8.dp, end = 8.dp),
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                fontFamily = FontFamily.Serif
            )
        },
        query = searchText,
        onQueryChange = { searchText = it },
        onSearch = { isActive = false },
        active = isActive,
        onActiveChange = { isActive = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = stringResource(
                    R.string.cont_desc_search_icon
                )
            )
        },
        trailingIcon = {
            if (isActive) {
                Icon(
                    modifier = Modifier.clickable {
                        if (searchText.isNotEmpty()) {
                            searchText = ""
                        } else {
                            isActive = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.cont_desc_close_icon)
                )
            }
        }
    ) {}
}

@Composable
fun FilmItem(films: ResultDTO) {

    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            var isInFavorite by rememberSaveable {
                mutableStateOf(false)
            }

            AsyncImage(
                model = AppModule.IMAGE_URL + films.poster_path,
                contentDescription = films.title,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.image_error),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(16.dp))
            )

            FloatingActionButton(
                onClick = {
                    if (isInFavorite == false) {
                        isInFavorite = true
                    } else {
                        isInFavorite = false
                    }
                },
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .size(44.dp)
                    .align(alignment = Alignment.BottomStart)
                    .padding(start = 4.dp, bottom = 4.dp),
                containerColor = Color(alpha = 0.5f, blue = 0.5f, red = 0.5f, green = 0.5f),
                content = {
                    if (isInFavorite == false) {
                        Image(
                            imageVector = Icons.Default.FavoriteBorder, contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    } else {
                        Image(
                            imageVector = Icons.Default.Favorite, contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.Red)
                        )
                    }

                }
            )
        }

        Text(
            text = films.title,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
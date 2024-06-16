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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.data.remote.ResultDTO
import com.example.movies.domain.di.AppModule
import com.example.movies.domain.navigation.BottomScreens
import com.example.movies.ui.theme.backForHomeScreen
import com.example.movies.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeScreen(viewModel: HomeViewModel, navHostController: NavHostController) {
    val allFilms = viewModel.allFilms.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(viewModel.showErrorToast) {
        viewModel.showErrorToast.collectLatest { toast ->
            if (toast) {
                Toast.makeText(
                    context,
                    getString(context, R.string.toast_request_error),
                    5000
                ).show()
            }

        }
    }

    if (allFilms.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backForHomeScreen),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 75.dp)
        ) {
            Search()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backForHomeScreen),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                userScrollEnabled = true,
                flingBehavior = ScrollableDefaults.flingBehavior(),
            ) {
                items(allFilms) { films ->
                    FilmItem(films, navHostController)
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
            .background(backForHomeScreen)
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
fun FilmItem(films: ResultDTO, navHostController: NavHostController) {
    var isInFavorite by rememberSaveable {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .clickable { navHostController.navigate(route = BottomScreens.Details.route) }
    ) {

        AsyncImage(
            model = AppModule.IMAGE_URL + films.poster_path,
            contentDescription = films.title,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.image_error),
            modifier = Modifier.clip(RoundedCornerShape(20.dp))
        )

        films.title?.let {
            Text(
                text = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp,
                        bottom = 3.dp
                    ),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                maxLines = 1,
                fontFamily = FontFamily.SansSerif,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                    .size(35.dp),
                containerColor = Color(0.5f, 0.5f, 0.5f, 0.5f),
                content = {
                    if (isInFavorite == false) {
                        Image(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(
                                R.string.cont_desc_out_from_favorites
                            ),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    } else {
                        Image(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = stringResource(
                                R.string.cont_desc_add_in_favorites
                            ),
                            colorFilter = ColorFilter.tint(Color.Red)
                        )
                    }

                }
            )

            RatingBar(
                starsModifier = Modifier
                    .size(20.dp),
                rating = films.vote_average?.div(2) ?: 3.5
            )

            FloatingActionButton(
                onClick = {},
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .size(35.dp),
                containerColor = Color(0.5f, 0.5f, 0.5f, 0.5f),
                content = {
                    Image(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(R.string.cont_desc_share_the_film),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            )
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    starsModifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow
) {
    val filledStars = kotlin.math.floor(rating).toInt()
    val outFilledStars = (stars - kotlin.math.ceil(rating)).toInt()
    val helfFilledStars = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.Rounded.Star,
                contentDescription = stringResource(R.string.cont_desc_rating_of_the_film),
                tint = starsColor
            )
        }
        if (helfFilledStars) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.AutoMirrored.Rounded.StarHalf,
                contentDescription = stringResource(R.string.cont_desc_rating_of_the_film),
                tint = starsColor
            )
        }
        repeat(outFilledStars) {
            Icon(
                modifier = starsModifier,
                imageVector = Icons.Rounded.StarOutline,
                contentDescription = stringResource(R.string.cont_desc_rating_of_the_film),
                tint = starsColor
            )
        }
    }
}
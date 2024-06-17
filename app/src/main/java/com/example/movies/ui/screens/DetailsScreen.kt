package com.example.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.domain.di.AppModule
import com.example.movies.ui.theme.backForDetailsScreen
import com.example.movies.ui.theme.cyan
import com.example.movies.ui.theme.pink
import com.example.movies.ui.theme.purple
import com.example.movies.ui.viewmodels.DetailsViewModel

@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {

    var isInFavorite by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backForDetailsScreen)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = AppModule.IMAGE_URL,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.image_error),
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "title",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            maxLines = 1,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "overview",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily.Cursive,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
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
                shape = RoundedCornerShape(30.dp),
                elevation = FloatingActionButtonDefaults.elevation(focusedElevation = 8.dp),
                modifier = Modifier
                    .size(45.dp),
                containerColor = pink,
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
                    .size(40.dp),
                rating = 3.6
            )


            FloatingActionButton(
                onClick = {},
                shape = RoundedCornerShape(30.dp),
                elevation = FloatingActionButtonDefaults.elevation(focusedElevation = 8.dp),
                modifier = Modifier
                    .size(45.dp),
                containerColor = purple,
                content = {
                    Image(
                        imageVector = Icons.Default.WatchLater,
                        contentDescription = stringResource(R.string.cont_desc_watch_later),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            )

            FloatingActionButton(
                onClick = {},
                shape = RoundedCornerShape(30.dp),
                elevation = FloatingActionButtonDefaults.elevation(focusedElevation = 8.dp),
                modifier = Modifier
                    .size(45.dp),
                containerColor = cyan,
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
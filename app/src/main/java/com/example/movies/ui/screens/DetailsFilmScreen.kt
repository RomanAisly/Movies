package com.example.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.movies.data.remote.ResultDTO
import com.example.movies.domain.di.AppModule

@Composable
fun DetailFilm(films: ResultDTO) {

    var isInFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .clickable { }
    ) {

        Box {
            AsyncImage(
                model = AppModule.IMAGE_URL + films.poster_path,
                contentDescription = films.title,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.image_error),
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
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
        }

        Text(
            text = films.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 4.dp,
                    bottom = 4.dp
                ),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            maxLines = 1,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 4.dp)
        ) {
            RatingBar(
                starsModifier = Modifier
                    .size(20.dp),
                rating = films.vote_average / 2
            )
        }

        Text(
            text = films.overview,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 4.dp,
                    bottom = 4.dp
                ),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
package com.example.movies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.ui.theme.gradForBack

@Composable
fun HomeScreen() {
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
        items(10) {
            FilmItem()
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
            .padding(start = 8.dp, end = 8.dp, bottom = 3.dp),
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
fun FilmItem() {
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://memepedia.ru/wp-content/uploads/2018/12/in_article_11341c19c0-768x768.jpg.",
            contentDescription = stringResource(id = R.string.cont_desc_movie_post),
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.image_error),
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(15.dp))
        )
        Text(
            text = "Name",
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 3.dp),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HomeScreen()
}
package com.example.movies.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.ui.theme.colorForSearchBarBackground

@Composable
fun ScreenForMain() {
    Column(modifier = Modifier.fillMaxSize()) {
        Search()
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
    SearchBar(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp),
        placeholder = { Text(text = stringResource(R.string.search_placeholder)) },
        query = searchText,
        onQueryChange = { text ->
            searchText = text
        },
        colors = SearchBarDefaults.colors(containerColor = colorForSearchBarBackground),
        onSearch = { isActive = false },
        active = false,
        onActiveChange = { isActive = it }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForMain() {
    ScreenForMain()
}
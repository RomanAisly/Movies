package com.example.movies.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.movies.R
import com.example.movies.domain.navigation.BottomNavBar
import com.example.movies.domain.navigation.NavGraph
import com.example.movies.ui.theme.colorForSearchBarBackground

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(bottomBar = { BottomNavBar(navController = navController) },
            topBar = { Search() }) {
            NavGraph(navHostController = navController)
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
    SearchBar(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp, end = 8.dp),
        placeholder = { Text(text = stringResource(R.string.search_placeholder)) },
        query = searchText,
        onQueryChange = { searchText = it },
        colors = SearchBarDefaults.colors(containerColor = colorForSearchBarBackground),
        onSearch = { isActive = false },
        active = isActive,
        onActiveChange = { isActive = it }
    ) {}
}


@Preview(showBackground = true)
@Composable
fun PreviewForMain() {
    HomeScreen()
}
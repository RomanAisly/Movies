package com.example.movies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WatchLaterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Search()
    }
}
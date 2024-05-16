package com.example.movies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.movies.ui.theme.gradForBack

@Composable
fun SettingsScreen() {
    LazyVerticalGrid(
        modifier = Modifier
            .wrapContentSize()
            .background(gradForBack),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(7.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true,
    ) {
        items(4) {
            SettingsItems()
        }
    }
}

@Composable
fun SettingsItems() {
    val setItems = listOf(
        SetItem(Icons.Default.AccountCircle, "Account"),
        SetItem(Icons.Default.Security, "Security"),
        SetItem(Icons.Default.Notifications, "Notifications"),
        SetItem(Icons.Default.Storage, "Storage"),
    )
    Column {

    }
}

data class SetItem(
    val icon: ImageVector,
    val name: String
)
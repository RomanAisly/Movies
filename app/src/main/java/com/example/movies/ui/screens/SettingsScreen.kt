package com.example.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen() {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(1) {
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
        setItems.forEach { item ->
            Image(
                imageVector = item.icon, contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .size(140.dp),
            )

            Text(
                text = item.name,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }

    }
}

data class SetItem(
    val icon: ImageVector,
    val name: String
)

@Preview(showBackground = true)
@Composable
fun PreviewSet() {
    SettingsScreen()
}
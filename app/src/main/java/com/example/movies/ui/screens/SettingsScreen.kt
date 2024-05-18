package com.example.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.R

@Composable
fun SettingsScreen() {
    SettingsItems()
}

@Composable
fun SettingsItems() {

    val setItems = listOf(
        SetItem(
            painterResource(id = R.drawable.settings_icon_account),
            stringResource(R.string.settings_account)
        ),
        SetItem(
            painterResource(id = R.drawable.settings_icon_security),
            stringResource(R.string.settings_security)
        ),
        SetItem(
            painterResource(id = R.drawable.settings_icon_notifications),
            stringResource(R.string.settings_notifications)
        ),
        SetItem(
            painterResource(id = R.drawable.settings_icon_storage),
            stringResource(R.string.settings_storage)
        )
    )
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        items(setItems) {
            Column {
                Image(
                    painter = it.icon,
                    contentDescription = stringResource(R.string.cont_desc_settings_icon),
                    modifier = Modifier
                        .fillMaxSize()
                        .size(140.dp),
                )

                Text(
                    text = it.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 3.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

data class SetItem(
    val icon: Painter,
    val name: String
)

@Preview(showBackground = true)
@Composable
fun PreviewSet() {
    SettingsScreen()
}
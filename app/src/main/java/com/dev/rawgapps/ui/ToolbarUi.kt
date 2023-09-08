package com.dev.rawgapps.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dev.rawgapps.common.CustomColor
import com.dev.rawgapps.common.CustomFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultToolbar(title: String, color: Color = CustomColor.NavyBlue, onFavoriteClick: (() -> Unit)={}) {
    TopAppBar(
        title = { Text(text = title.uppercase(), color = Color.White, fontFamily = CustomFontFamily.InterFontFamily, fontWeight = FontWeight.Bold) },
        actions = {
            IconButton(onClick = onFavoriteClick) {
                Icon(imageVector = Icons.Filled.Favorite, tint = Color.White, contentDescription = "Icon Favorite")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = color
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContentToolbar(color: Color = Color.Black, onBackClick:()->Unit={}, onFavoriteClick: (() -> Unit)={}) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Filled.ArrowBack, tint = Color.White, contentDescription = "Icon Back")
            }
        },
        actions = {
            IconButton(onClick = onFavoriteClick) {
                Icon(imageVector = Icons.Filled.Favorite, tint = Color.White, contentDescription = "Icon Favorite")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = color
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteToolbar(title: String, color: Color = CustomColor.NavyBlue, onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title.uppercase(), color = Color.White, fontFamily = CustomFontFamily.InterFontFamily, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Filled.ArrowBack, tint = Color.White, contentDescription = "Icon Favorite")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = color
        )
    )
}
@Composable
@Preview(showBackground = true, device = Devices.NEXUS_5)
fun TopAppBarDetailContentToolbarPreview() {
    DetailContentToolbar(onBackClick = {

    }, onFavoriteClick = {

    })
}
@Composable
@Preview(showBackground = true, device = Devices.NEXUS_5)
fun TopAppBarPreview() {
    DefaultToolbar(title = "Rawg games", onFavoriteClick = {

    })
}

@Composable
@Preview(showBackground = true, device = Devices.NEXUS_5)
fun TopAppBarFavoriteToolbarPreview() {
    FavoriteToolbar("Favorite games",onBackClick = {

    })
}
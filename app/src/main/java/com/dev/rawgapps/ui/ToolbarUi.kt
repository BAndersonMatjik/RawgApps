package com.dev.rawgapps.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dev.rawgapps.R
import com.dev.rawgapps.common.CustomColor
import com.dev.rawgapps.common.CustomFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultToolbar(
    title: String,
    color: Color = CustomColor.NavyBlue,
    onFavoriteClick: (() -> Unit) = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title.uppercase(),
                modifier = Modifier.semantics { contentDescription = title.uppercase() },
                color = Color.White,
                fontFamily = CustomFontFamily.InterFontFamily,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    tint = Color.White,
                    contentDescription = "Icon Favorite"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = color
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContentToolbar(
    isFavorite: Boolean = false,
    color: Color = Color.Transparent,
    onBackClick: () -> Unit = {},
    onFavoriteClick: (() -> Unit) = {},
    showFavoriteIcon:Boolean =false
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Icon Back"
                )
            }
        },
        actions = {
            IconButton(onClick = onFavoriteClick) {
                if (showFavoriteIcon){
                    if (isFavorite) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            tint = Color.White,
                            contentDescription = "Icon Favorite"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            tint = Color.White,
                            contentDescription = "Icon Favorite"
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = color
        )
    )
}


@Composable
fun ToolbarImageView(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onFavoriteClick: (() -> Unit) = {},
    imageUrl: String = "",
    contentDescription: String = "",
    isFavorite: Boolean = false,
    showFavoriteIcon:Boolean=false,
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            placeholder = debugPlaceholder(R.drawable.placeholder_game)
        )
        DetailContentToolbar(
            isFavorite = isFavorite,
            onBackClick = onBackClick,
            onFavoriteClick = onFavoriteClick,
            showFavoriteIcon = showFavoriteIcon
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteToolbar(title: String, color: Color = CustomColor.NavyBlue, onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title.uppercase(),
                color = Color.White,
                fontFamily = CustomFontFamily.InterFontFamily,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Icon Favorite"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = color
        )
    )
}

@Composable
@Preview(showBackground = true, device = Devices.NEXUS_5)
fun ToolbarImageViewPreview() {
    ToolbarImageView(modifier = Modifier.height(200.dp))
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
    FavoriteToolbar("Favorite games", onBackClick = {

    })
}
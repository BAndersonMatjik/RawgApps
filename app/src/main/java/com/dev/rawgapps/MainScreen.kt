package com.dev.rawgapps

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.rawgapps.feature.favoritegame.FavoriteGameScreen
import com.dev.rawgapps.feature.favoritegame.FavoriteGameScreens
import com.dev.rawgapps.feature.game.DetailGameScreen
import com.dev.rawgapps.feature.game.GameScreen

@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DestinationRoute.GameScreen.route, builder = {
        composable(DestinationRoute.GameScreen.route){
            GameScreen()
        }
        composable(DestinationRoute.FavoriteGameScreen.route){
            DetailGameScreen()
        }
        composable(DestinationRoute.DetailGameScreen.route){
            FavoriteGameScreen()
        }
    })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultToolbar(title: String, onClickFavorite: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        actions = {
            IconButton(onClick = onClickFavorite) {
                Icon(imageVector = Icons.Filled.Favorite, tint = Color.White, contentDescription = "Icon Favorite")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(
                id = R.color.purple_500
            )
        )
    )
}

@Composable
@Preview(showBackground = true, device = Devices.NEXUS_5)
fun TopAppBarPreview() {
    DefaultToolbar(title = "Test", onClickFavorite = {

    })
}
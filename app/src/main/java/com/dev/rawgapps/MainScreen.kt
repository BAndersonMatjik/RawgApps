package com.dev.rawgapps

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.rawgapps.common.CustomColor
import com.dev.rawgapps.common.CustomFontFamily
import com.dev.rawgapps.feature.favoritegame.FavoriteGameScreen
import com.dev.rawgapps.feature.game.DetailGameScreen
import com.dev.rawgapps.feature.game.GameRoute
import com.dev.rawgapps.feature.game.GameScreen

@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DestinationRoute.GameScreen.route, builder = {
        composable(DestinationRoute.GameScreen.route){
            GameRoute(navigateToFavorite = {
                navController.navigate(DestinationRoute.FavoriteGameScreen.route)
            })
        }
        composable(DestinationRoute.FavoriteGameScreen.route){
            DetailGameScreen()
        }
        composable(DestinationRoute.DetailGameScreen.route){
            FavoriteGameScreen()
        }
    })
}

@Composable
@Preview(showBackground = true, device = Devices.NEXUS_5)
fun MainScreenPreview(){
    val navController = rememberNavController()
    MainScreen(navController = navController)
}






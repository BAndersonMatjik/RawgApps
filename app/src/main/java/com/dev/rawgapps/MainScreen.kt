package com.dev.rawgapps

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.rawgapps.common.ui.GameParamType
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.feature.favoritegame.FavoriteGameRoute
import com.dev.rawgapps.feature.game.DetailGameRoute
import com.dev.rawgapps.feature.game.GameRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = DestinationRoute.GameScreen.route,
        builder = {
            composable(DestinationRoute.GameScreen.route) {
                GameRoute(navigateToFavorite = {
                    navController.navigate(DestinationRoute.FavoriteGameScreen.route)
                }, navigateToDetailGame = {
                    val json = Json.encodeToString(it)
                    val detailGameRoute = DestinationRoute.DetailGameScreen.route.replace(
                        oldValue = "{slug}",
                        newValue = it.slug
                    ).replace(oldValue = "{game}", newValue = json)
                    navController.navigate(detailGameRoute)
                })
            }
            composable(
                DestinationRoute.DetailGameScreen.route,
                arguments = listOf(
                    navArgument("slug") {
                        type = NavType.StringType
                    },
                    navArgument("game") {
                        type = GameParamType()
                    }),
            ) {
                val slug = it.arguments?.getString("slug")
                val game = it.arguments?.getParcelable<Game>("game")
                if (slug.isNullOrBlank() && game==null){
                    ShowToastWithComposable(message = "Failed To Found Game Detail")
                }else{
                    DetailGameRoute(
                        game = game!!, slug = slug!!,
                        navigateBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
            composable(DestinationRoute.FavoriteGameScreen.route) {
                FavoriteGameRoute()
            }
        })
}

@Composable
fun ShowToastWithComposable(message:String, duration:Int=Toast.LENGTH_SHORT){
    Toast.makeText(LocalContext.current,message,duration).show()
}


@Composable
@Preview(showBackground = true, device = Devices.NEXUS_5)
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}






package com.dev.rawgapps

sealed class DestinationRoute(val route:String){
    object GameScreen:DestinationRoute("gameScreen")
    object DetailGameScreen:DestinationRoute("detailGameScreen")
    object FavoriteGameScreen:DestinationRoute("favoriteGameScreen")
}

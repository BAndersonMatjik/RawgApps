package com.dev.rawgapps.feature.favoritegame

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dev.rawgapps.ui.theme.RawgAppsTheme

@Composable
fun FavoriteGameScreen(){

}


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun FavoriteGameScreenPreview() {
    RawgAppsTheme {
        FavoriteGameScreen()
    }
}
package com.dev.rawgapps.feature.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.rawgapps.ui.DefaultToolbar
import com.dev.rawgapps.ui.SearchTextField
import com.dev.rawgapps.ui.theme.RawgAppsTheme

@Composable
fun GameScreen(navigateToFavorite:()->Unit){

    Column(modifier = Modifier.fillMaxSize()) {
        DefaultToolbar(title = "Rawg Game", onFavoriteClick = navigateToFavorite)
        SearchTextField(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
    }
}



@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun GameScreenPreview() {
    RawgAppsTheme {
        GameScreen(navigateToFavorite = {})
    }
}



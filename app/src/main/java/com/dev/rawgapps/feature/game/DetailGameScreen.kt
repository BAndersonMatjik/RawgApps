package com.dev.rawgapps.feature.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.ui.theme.RawgAppsTheme

@Composable
fun DetailGameScreen(
    game: Game?,
    slug: String?
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = slug.toString())
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = game.toString())
    }
}


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun DetailGameScreenPreview() {
    RawgAppsTheme {
//        DetailGameScreen()
    }
}
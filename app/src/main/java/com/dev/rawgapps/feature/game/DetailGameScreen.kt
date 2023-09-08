package com.dev.rawgapps.feature.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dev.rawgapps.ui.theme.RawgAppsTheme

@Composable
fun DetailGameScreen(){

}


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun DetailGameScreenPreview() {
    RawgAppsTheme {
        DetailGameScreen()
    }
}
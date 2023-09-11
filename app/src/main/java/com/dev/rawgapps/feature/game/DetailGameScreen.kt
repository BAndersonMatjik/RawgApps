package com.dev.rawgapps.feature.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.rawgapps.ShowToastWithComposable
import com.dev.rawgapps.common.CustomFontFamily
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.ui.ToolbarImageView
import com.dev.rawgapps.ui.theme.RawgAppsTheme
import timber.log.Timber


@Composable
internal fun DetailGameRoute(
    viewModel: DetailGameViewModel = hiltViewModel(),
    navigateBack: () -> Unit = {},
    game: Game,
    slug: String,
) {
    LaunchedEffect(Unit) {
        viewModel.apply {
            onEvent(DetailGameViewModel.DetailGameEvent.Init(game = game))
            onEvent(DetailGameViewModel.DetailGameEvent.GetDetailGame(slug))
        }
    }
    DetailGameScreen(onBackClick = navigateBack, onFavoriteClick = {
        //event send flag is my favorite
        viewModel.onEvent(DetailGameViewModel.DetailGameEvent.SaveIsFavorite)
        Timber.d("Click Favorite")
    }, uiState = viewModel.uiState.value)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailGameScreen(
    onBackClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    uiState: DetailGameViewModel.DetailGameViewState = DetailGameViewModel.DetailGameViewState()
) {
    val game = uiState.game
    if (uiState.errorFetchDetailGame.isNotEmpty()) {
        ShowToastWithComposable(message = uiState.errorFetchDetailGame)
    }
    if (uiState.showFavoriteIcon){
        if (uiState.messageToast.isNotEmpty()){
            ShowToastWithComposable(message = uiState.messageToast)
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            ToolbarImageView(
                isFavorite = uiState.game.isFavorite,
                imageUrl = uiState.game.backgroundImage,
                modifier = Modifier.height(200.dp),
                onBackClick = onBackClick,
                onFavoriteClick = onFavoriteClick,
                showFavoriteIcon = uiState.showFavoriteIcon
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = uiState.game.name,
                    fontFamily = CustomFontFamily.InterFontFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Release Date: ${game.released}",
                    fontFamily = CustomFontFamily.InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Genres: ${game.genres.joinToString()}",
                    fontFamily = CustomFontFamily.InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Developer: ${game.developer}",
                    fontFamily = CustomFontFamily.InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Description",
                    fontFamily = CustomFontFamily.InterFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = game.description,
                    fontFamily = CustomFontFamily.InterFontFamily,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.labelSmall
                )
            }

        }
    }
}


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun DetailGameScreenPreview() {
    RawgAppsTheme {
        DetailGameScreen(
            uiState = DetailGameViewModel.DetailGameViewState(
                isLoading = false, errorFetchDetailGame = "", game = Game(
                    slug = "GTA-V",
                    name = "GTA V",
                    genres = listOf("Action", "Rpg"),
                    released = "2013-09-17",
                    backgroundImage = "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
                    description = "Sired in an act of vampire insurrection, your existence ignites the war for Seattle's blood trade. Enter uneasy alliances with the creatures who control the city and uncover the sprawling conspiracy which plunged Seattle into a bloody civil war between powerful vampire factions. ♞Become the Ultimate Vampire Immerse yourself in the World of Darkness and live out your vampire fantasy in a city filled with intriguing characters that react to your choices. You and your unique disciplines are a weapon in our forward-driving, fast-moving, melee-focussed combat system. Your power will grow as you advance, but remember to uphold the Masquerade and guard your humanity... or face the consequences.",
                    developer = "developer"
                )
            )
        )
    }
}
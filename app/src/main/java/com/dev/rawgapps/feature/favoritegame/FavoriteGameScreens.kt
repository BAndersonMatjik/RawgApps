package com.dev.rawgapps.feature.favoritegame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dev.rawgapps.common.PhonePreviews
import com.dev.rawgapps.common.ui.GameScreenPreviewParameterProvider
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.ui.FavoriteToolbar
import com.dev.rawgapps.ui.GameCard
import com.dev.rawgapps.ui.theme.RawgAppsTheme
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun FavoriteGameRoute(
    mViewModel: FavoriteGameViewModel = hiltViewModel(),
    navigateToDetailGame: (Game) -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    val gamesFavorite = mViewModel.gamesState.collectAsLazyPagingItems()
    FavoriteGameScreen(navigateBack = navigateBack, gamePagingItems = gamesFavorite, navigateToDetailGame = navigateToDetailGame)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteGameScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    navigateBack: () -> Unit = {},
    navigateToDetailGame: (Game) -> Unit = {},
    gamePagingItems: LazyPagingItems<Game>
) {
    Scaffold(topBar = {
        FavoriteToolbar(title = "Favorite Games", onBackClick = navigateBack)
    }) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(gamePagingItems.itemCount) { index ->
                gamePagingItems[index]?.apply {
                    GameCard(
                        game = this, modifier = Modifier
                            .fillMaxWidth(), onItemClick = navigateToDetailGame
                    )
                }
            }

            gamePagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { Text(text = "Loading - LOADING") }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = gamePagingItems.loadState.refresh as LoadState.Error
                        item { Text(text = "error ${error.error.message.toString()}") }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { Text(text = "Loading - APPEND") }
                    }

                    loadState.append is LoadState.Error -> {
                        item { Text(text = "error") }
                    }
                }
            }
        }
    }
}


@PhonePreviews
@Composable
fun FavoriteGameScreenPreview(@PreviewParameter(GameScreenPreviewParameterProvider::class) gamePagingItems: MutableStateFlow<PagingData<Game>>) {
    RawgAppsTheme {
        FavoriteGameScreen(gamePagingItems = gamePagingItems.collectAsLazyPagingItems(), navigateBack = {})
    }
}
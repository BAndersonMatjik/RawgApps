package com.dev.rawgapps.feature.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dev.rawgapps.domain.Game
import com.dev.rawgapps.ui.DefaultToolbar
import com.dev.rawgapps.ui.GameCard
import com.dev.rawgapps.ui.SearchTextField
import com.dev.rawgapps.ui.theme.RawgAppsTheme
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
internal fun GameRoute(
    viewModel: GameViewModel = hiltViewModel(),
    navigateToFavorite: () -> Unit = {},
    navigateToDetailGame: (Game) -> Unit = {}
) {
    val gamePagingItems: LazyPagingItems<Game> = viewModel.gamesState.collectAsLazyPagingItems()
    GameScreen(
        gamePagingItems = gamePagingItems,
        navigateToFavorite = navigateToFavorite,
        navigateToDetailGame = navigateToDetailGame
    )
}


@Composable
fun GameScreen(
    gamePagingItems: LazyPagingItems<Game>,
    navigateToFavorite: () -> Unit = {},
    navigateToDetailGame: (Game) -> Unit = {}
) {

    Column(modifier = Modifier.fillMaxSize()) {
        DefaultToolbar(title = "Rawg Game", onFavoriteClick = navigateToFavorite)
        SearchTextField(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            hint = "Search Game"
        )
        LazyColumn(
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
                        item { Text(text = "error") }
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


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun GameScreenPreview() {
    val fakeListGame: MutableStateFlow<PagingData<Game>> = MutableStateFlow(
        value = PagingData.from(
            listOf(
                Game(
                    slug = "class",
                    name = "Rita Freeman",
                    genre = listOf("RPG", "ACTION"),
                    released = "vis",
                    backgroundImage = "tractatos",
                    description = "lacus"
                ), Game(
                    slug = "class",
                    name = "Rita Freeman",
                    genre = listOf("RPG", "ACTION"),
                    released = "vis",
                    backgroundImage = "tractatos",
                    description = "lacus"
                )
            )
        )
    )
    RawgAppsTheme {
        GameScreen(
            gamePagingItems = fakeListGame.collectAsLazyPagingItems(),
            navigateToFavorite = {},
            navigateToDetailGame = {})
    }
}



package com.dev.rawgapps.feature.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.dev.rawgapps.ui.DefaultToolbar
import com.dev.rawgapps.ui.GameCard
import com.dev.rawgapps.ui.SearchTextField
import com.dev.rawgapps.ui.theme.RawgAppsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
internal fun GameRoute(
    viewModel: GameViewModel = hiltViewModel(),
    navigateToFavorite: () -> Unit = {},
    navigateToDetailGame: (Game) -> Unit = {},
) {
    val gamePagingItems: LazyPagingItems<Game> = viewModel.gamesState.collectAsLazyPagingItems()
    GameScreen(
        gamePagingItems = gamePagingItems,
        navigateToFavorite = navigateToFavorite,
        navigateToDetailGame = navigateToDetailGame,
        onSearchTextChange ={
            viewModel.onEvent(GameViewModel.GameEvent.SearchGames(it))
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GameScreen(
    gamePagingItems: LazyPagingItems<Game>,
    navigateToFavorite: () -> Unit = {},
    navigateToDetailGame: (Game) -> Unit = {},
    onSearchTextChange:(String)->Unit= {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            keyboardController?.hide()
        }
    }

    Scaffold(topBar={
        DefaultToolbar(title = "Rawg Game", onFavoriteClick = navigateToFavorite)
    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            SearchTextField(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                hint = "Search Game",
                onTextChange = onSearchTextChange
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

}


@PhonePreviews()
@Composable
fun GameScreenPreview(@PreviewParameter(GameScreenPreviewParameterProvider::class) gamePagingItems:MutableStateFlow<PagingData<Game>>) {
    RawgAppsTheme {
        GameScreen(
            gamePagingItems = gamePagingItems.collectAsLazyPagingItems(),
            navigateToFavorite = {},
            navigateToDetailGame = {})
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClearKeyboard(){
    val focusManager = LocalSoftwareKeyboardController.current
    focusManager?.hide()
}
@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex = remember{ mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset = remember{ mutableStateOf(firstVisibleItemScrollOffset) }
    return remember {
        derivedStateOf {
            if (previousIndex.value != firstVisibleItemIndex) {
                previousIndex.value < firstVisibleItemIndex
            } else {
                previousScrollOffset.value <= firstVisibleItemScrollOffset
            }.also {
                previousIndex.value = firstVisibleItemIndex
                previousScrollOffset.value = firstVisibleItemScrollOffset
            }
        }
    }.value
}

package com.dev.rawgapps.feature.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.dev.rawgapps.ShowToastWithComposable
import com.dev.rawgapps.common.CustomColor
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
        onSearchTextChange = {
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
    onSearchTextChange: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val errorMessage = remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            keyboardController?.hide()
        }
    }
    if (errorMessage.value.isNotBlank()) {
        ShowToastWithComposable(message = errorMessage.value)
    }

    Scaffold(topBar = {
        DefaultToolbar(title = "Rawg Games", onFavoriteClick = navigateToFavorite)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SearchTextField(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                hint = "Search Games",
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
                            item {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    LinearProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        color = CustomColor.NavyBlue
                                    )
                                }
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = gamePagingItems.loadState.refresh as LoadState.Error
                            item {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Error :: ${error.error.message.toString()}", modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center)
                                    Button(onClick = {
                                        gamePagingItems.retry()
                                    }, content = {
                                        Text(text = "Retry")
                                    })
                                    
                                }
                            }
                            errorMessage.value = error.error.message.toString()
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    LinearProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        color = CustomColor.NavyBlue
                                    )
                                }
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            item {
                                Column {
                                    Text(
                                        text = "Not Found More Data",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                    Button(onClick = {
                                        gamePagingItems.retry()
                                    }, content = {
                                        Text(text = "Retry")
                                    })
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}


@PhonePreviews()
@Composable
fun GameScreenPreview(@PreviewParameter(GameScreenPreviewParameterProvider::class) gamePagingItems: MutableStateFlow<PagingData<Game>>) {
    RawgAppsTheme {
        GameScreen(
            gamePagingItems = gamePagingItems.collectAsLazyPagingItems(),
            navigateToFavorite = {},
            navigateToDetailGame = {})
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClearKeyboard() {
    val focusManager = LocalSoftwareKeyboardController.current
    focusManager?.hide()
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex = remember { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset = remember { mutableStateOf(firstVisibleItemScrollOffset) }
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

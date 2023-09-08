package com.dev.rawgapps.common.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.PagingData
import com.dev.rawgapps.domain.Game
import kotlinx.coroutines.flow.MutableStateFlow

class GameScreenPreviewParameterProvider :
    PreviewParameterProvider<MutableStateFlow<PagingData<Game>>> {
    override val values: Sequence<MutableStateFlow<PagingData<Game>>>
        get() {
            return sequenceOf(
                MutableStateFlow(
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
            )

        }
}
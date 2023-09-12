package com.dev.rawgapps.utils

import com.dev.rawgapps.domain.Game

object FakeData {
    fun getGame()= Game(
        slug = "Forza Motorsport",
        name = "Forza Motorsport",
        genres = listOf("Racing"),
        released = "2023-10-10",
        backgroundImage = "https://media.rawg.io/media/games/e9e/e9ecc560712ebfa6761e45d2523b0fb9.jpg",
        description = "we love racing ",
        developer = "Playground Games",
        isFavorite = false
    )
}
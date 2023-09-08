package com.dev.rawgapps.domain

data class Game(
    val slug:String,
    val name:String,
    val genre:List<String>,
    val released:String,
    val backgroundImage:String,
    val description:String
)
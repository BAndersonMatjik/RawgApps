package com.dev.rawgapps.common.ui

import android.os.Bundle
import androidx.navigation.NavType
import com.dev.rawgapps.domain.Game
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GameParamType:NavType<Game>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Game? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Game {
      return Json.decodeFromString<Game>(value)
    }

    override fun put(bundle: Bundle, key: String, value: Game) {
       bundle.putParcelable(key,value)
    }
}
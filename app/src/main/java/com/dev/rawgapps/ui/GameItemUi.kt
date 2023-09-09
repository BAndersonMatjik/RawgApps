package com.dev.rawgapps.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dev.rawgapps.R
import com.dev.rawgapps.common.CustomFontFamily
import com.dev.rawgapps.domain.Game


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    game: Game,
    modifier: Modifier = Modifier,
    cardColors: CardColors = CardDefaults.cardColors(containerColor = Color.White),
    onItemClick:(Game)->Unit={}
) {
    var isClick by remember{
        mutableStateOf(false)
    }
    isClick.useDebounce(delayMillis = 200L,onChange = {
        if (it){
            onItemClick(game)
        }
    })
    Card(
        modifier = modifier.shadow(
            ambientColor = Color.Gray.copy(alpha = 0.8F),
            elevation = 10.dp
        ), colors = cardColors,
        shape = CardDefaults.elevatedShape,
        onClick ={
            isClick = true
        }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(game.backgroundImage)
                .crossfade(true)
                .build(),
            contentDescription = game.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            placeholder = debugPlaceholder(R.drawable.placeholder_game)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = game.name,
                fontFamily = CustomFontFamily.InterFontFamily,
                fontWeight = FontWeight.Black,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Release Date: ${game.released}",
                fontFamily = CustomFontFamily.InterFontFamily,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Genres: ${game.genre.joinToString()}",
                fontFamily = CustomFontFamily.InterFontFamily,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }

@Composable
@Preview(showBackground = true, device = Devices.NEXUS_5)
fun GameCardPreview() {
    val fake = Game(
        slug = "GTA-V",
        name = "GTA V",
        genre = listOf("Action", "Rpg"),
        released = "2013-09-17",
        backgroundImage = "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
        description = "?"
    )
    GameCard(
        game = fake, modifier = Modifier
            .fillMaxWidth()

    )
}
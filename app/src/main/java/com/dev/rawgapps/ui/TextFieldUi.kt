package com.dev.rawgapps.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.rawgapps.common.CustomFontFamily
import com.dev.rawgapps.ui.theme.RawgAppsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    cardColors: CardColors = CardDefaults.cardColors(containerColor = Color.White),
    hint:String = "Hint",
    onTextChange:(String)->Unit={}
) {
    var text by rememberSaveable { mutableStateOf("") }
    var isFocus by rememberSaveable {
        mutableStateOf(false)
    }

    text.useDebounce(onChange = {
        onTextChange(it)
    })
    Card(modifier = modifier
        .shadow(ambientColor = Color.Gray.copy(alpha = 0.8F), elevation = 10.dp)
        .onFocusChanged {
            isFocus = it.isFocused
        }, shape = CardDefaults.elevatedShape, colors = cardColors) {
        BasicTextField(
            modifier = Modifier.fillMaxWidth().testTag("text_field_search"),
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            singleLine = true,
            decorationBox = {
                Row(
                    modifier = Modifier.padding(all = 16.dp), // inner padding
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "icon search")
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    if (text.isEmpty()&&!isFocus){
                        Text(text= hint, fontFamily = CustomFontFamily.InterFontFamily, fontWeight = FontWeight.Normal)
                    }else{
                        it()
                    }
                }
            }
        )
    }
}

@Composable
fun <T> T.useDebounce(
    delayMillis: Long = 400L,
    // 1. couroutine scope
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onChange: (T) -> Unit
): T{
    // 2. updating state
    val state by rememberUpdatedState(this)

    // 3. launching the side-effect handler
    DisposableEffect(state){
        val job = coroutineScope.launch {
            delay(delayMillis)
            onChange(state)
        }
        onDispose {
            job.cancel()
        }
    }
    return state
}
@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun SearchTextFieldPreview() {
    RawgAppsTheme {
        SearchTextField(hint = "Search games")
    }
}

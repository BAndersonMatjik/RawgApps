package com.dev.rawgapps.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.dev.rawgapps.R

object CustomFontFamily {
    val InterFontFamily = FontFamily(
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_extralight, FontWeight.ExtraLight),
        Font(R.font.inter_light, FontWeight.Light),
        Font(R.font.inter_bold, FontWeight.Bold),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_extrabold, FontWeight.ExtraBold),
        Font(R.font.inter_thin, FontWeight.Thin),
        Font(R.font.inter_black, FontWeight.Black),
        Font(R.font.inter_medium, FontWeight.Medium),
    )
}
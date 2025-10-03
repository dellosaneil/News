package com.thelazybattley.core.ui.theme

import androidx.compose.ui.graphics.Color

data class NewsColors(
    val grayScale: Color,
    val primary: Color,
    val black: Color
)

fun lightColors(
    grayScale: Color = MulledWine,
    primary: Color = AzureRadiance,
    black: Color = Black
): NewsColors {
    return NewsColors(
        grayScale = grayScale,
        primary = primary,
        black = black
    )
}

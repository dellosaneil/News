package com.thelazybattley.core.ui.theme

import androidx.compose.ui.graphics.Color

data class NewsColors(
    val grayScale: Color,
    val primary: Color,
    val black: Color,
    val white: Color,
    val transparent: Color
)

fun lightColors(
    grayScale: Color = MulledWine,
    primary: Color = AzureRadiance,
    black: Color = Black,
    white: Color = White,
    transparent: Color = Transparent
): NewsColors {
    return NewsColors(
        grayScale = grayScale,
        primary = primary,
        black = black,
        white = white,
        transparent = transparent
    )
}

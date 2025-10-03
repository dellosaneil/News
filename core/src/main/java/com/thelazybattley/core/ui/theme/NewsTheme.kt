package com.thelazybattley.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


val LocalNewsColors = staticCompositionLocalOf<NewsColors> {
    error("No colors provided")
}

val LocalNewsTypography = staticCompositionLocalOf<NewsTypography> {
    error("No typography provided")
}

@Composable
fun NewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalNewsColors provides lightColors(),
        LocalNewsTypography provides newsTypography()
    ) {
        content()
    }
}

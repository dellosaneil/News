package com.thelazybattley.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.thelazybattley.core.R


data class NewsTypography(
    val smallText: TextStyle,
    val xSmallText: TextStyle,
    val mediumText: TextStyle,
    val mediumLink: TextStyle,
    val xSmallLink: TextStyle
)

fun newsTypography(): NewsTypography {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val fontName = GoogleFont("Poppins")
    val fontFamily = FontFamily(
        Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.SemiBold),
        Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Normal)
    )
    return NewsTypography(
        smallText = TextStyle(
            lineHeight = 21.sp,
            fontSize = 14.sp,
            letterSpacing = 0.12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal
        ),
        mediumLink = TextStyle(
            lineHeight = 24.sp,
            fontSize = 16.sp,
            letterSpacing = 0.12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold
        ),
        xSmallText = TextStyle(
            lineHeight = (19.5).sp,
            fontSize = 13.sp,
            letterSpacing = 0.12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal
        ),
        mediumText = TextStyle(
            lineHeight = 24.sp,
            fontSize = 16.sp,
            letterSpacing = 0.12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal
        ),
        xSmallLink = TextStyle(
            lineHeight = (19.5).sp,
            fontSize = 13.sp,
            letterSpacing = 0.12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold
        )
    )
}

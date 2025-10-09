package com.thelazybattley.feature.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.core.ui.theme.LocalNewsTypography
import com.thelazybattley.core.ui.theme.NewsTheme
import com.thelazybattley.feature.R

@Composable
fun CommonEmptyNews(
    modifier: Modifier = Modifier,
    title: String,
    subtext: String
) {
    val typography = LocalNewsTypography.current
    val color = LocalNewsColors.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        GlideImage(
            imageModel = {
                R.drawable.ic_no_news_found
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit
            )
        )
        Text(
            text = title,
            style = typography.mediumText.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = color.black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = subtext,
            style = typography.smallText,
            color = color.black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommonEmptyNews() {
    NewsTheme {
        CommonEmptyNews(
            modifier = Modifier,
            title = "No news found",
            subtext = "Try searching with different keywords or check your spelling."
        )
    }
}

package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.network.data.news.NewsSource
import com.thelazybattley.core.ui.shimmerEffect
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.core.ui.theme.LocalNewsTypography
import com.thelazybattley.core.ui.theme.NewsTheme
import com.thelazybattley.feature.util.CommonSourceDetails

@Composable
fun NewsDetailsPreview(
    modifier: Modifier = Modifier,
    article: NewsArticle
) {
    val typography = LocalNewsTypography.current
    val color = LocalNewsColors.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
    ) {
        val imageModifier = Modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .size(width = 120.dp, height = 100.dp)
        GlideImage(
            imageModel = {
                article.urlToImage
            },
            loading = {
                Spacer(
                    modifier = Modifier
                        .shimmerEffect()
                        .then(
                            other = imageModifier
                        )
                )
            },
            modifier = imageModifier,
            failure = {
                Spacer(
                    modifier = Modifier
                        .shimmerEffect()
                        .then(
                            other = imageModifier
                        )
                )
            }
        )
        Column(
            modifier = Modifier.weight(weight = 1f),
            verticalArrangement = Arrangement.spacedBy(space = 4.dp)
        ) {
            Text(
                text = article.author,
                style = typography.xSmallText,
                color = color.grayScale
            )
            Text(
                text = article.title,
                style = typography.mediumText,
                color = color.black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            CommonSourceDetails(
                modifier = Modifier.fillMaxWidth(),
                article = article
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewsDetailsPreview() {
    NewsTheme {
        NewsDetailsPreview(
            modifier = Modifier.fillMaxWidth(),
            article = NewsArticle(
                author = "author",
                content = "content",
                description = "description",
                publishedAt = "",
                source = NewsSource(
                    id = "",
                    name = "News Source",
                    imageUrl = "https://sportshub.cbsistatic.com/i/r/2025/09/30/502b0cbc-3743-45bd-9e2c-c0b7653b2b1e/thumbnail/1200x675/134de9ae08c980dab57d37946699f896/aaron-judge-new-york-yankees-imagn-images-8.jpg"
                ),
                title = "Title",
                url = "",
                urlToImage = "https://sportshub.cbsistatic.com/i/r/2025/09/30/502b0cbc-3743-45bd-9e2c-c0b7653b2b1e/thumbnail/1200x675/134de9ae08c980dab57d37946699f896/aaron-judge-new-york-yankees-imagn-images-8.jpg",
                timePassed = "a day ago"
            )
        )
    }
}

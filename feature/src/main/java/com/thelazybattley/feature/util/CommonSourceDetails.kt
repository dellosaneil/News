package com.thelazybattley.feature.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
import com.thelazybattley.feature.R

@Composable
fun CommonSourceDetails(modifier: Modifier = Modifier, article: NewsArticle) {
    val typography = LocalNewsTypography.current
    val color = LocalNewsColors.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = { article.source.imageUrl },
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(size = 20.dp),
            loading = {
                Spacer(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .shimmerEffect()
                        .size(size = 20.dp)
                )
            },
            failure = {
                Spacer(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .shimmerEffect()
                        .size(size = 20.dp)
                )
            }
        )
        Text(
            text = article.source.name,
            style = typography.xSmallText,
            color = color.grayScale,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Icon(
            modifier = Modifier.size(size = 14.dp),
            contentDescription = null,
            painter = painterResource(id = R.drawable.ic_time)
        )
        Text(
            text = article.timePassed,
            style = typography.xSmallText,
            color = color.grayScale,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@Composable
fun ShimmerCommonSourceDetails(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .clip(
                    shape = CircleShape
                )
                .shimmerEffect()
                .size(size = 20.dp)
        )
        Spacer(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .shimmerEffect()
                .size(width = 24.dp, height = 14.dp)
        )
        Spacer(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .shimmerEffect()
                .size(size = 14.dp)
        )
        Spacer(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .shimmerEffect()
                .height(14.dp)
                .weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommonSourceDetails() {
    NewsTheme {
        CommonSourceDetails(
            modifier = Modifier.fillMaxWidth(), article = NewsArticle(
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

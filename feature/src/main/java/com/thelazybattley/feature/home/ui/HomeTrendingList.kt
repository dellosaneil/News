@file:Suppress("DEPRECATION")

package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.thelazybattley.core.network.data.news.News
import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.network.data.news.NewsSource
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.core.ui.theme.LocalNewsTypography
import com.thelazybattley.core.ui.theme.NewsTheme
import com.thelazybattley.feature.R

@Composable
fun HomeTrendingList(
    modifier: Modifier = Modifier,
    news: News?
) {
    val typography = LocalNewsTypography.current
    val colors = LocalNewsColors.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.trending),
                style = typography.mediumLink,
                color = colors.black
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.see_all),
                style = typography.smallText,
                color = colors.grayScale
            )
        }
        if (news == null) {
            return@Column
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            items(items = news.articles) { article ->
                TrendingNewsCard(
                    article = article
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun TrendingNewsCard(
    modifier: Modifier = Modifier,
    article: NewsArticle
) {
    val typography = LocalNewsTypography.current
    val color = LocalNewsColors.current
    Card(
        modifier = modifier.width(275.dp),
        shape = RoundedCornerShape(size = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.white
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            modifier = Modifier.padding(all = 8.dp)
        ) {
            GlideImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .height(150.dp)
                    .fillMaxWidth()

            )
            Text(
                text = article.author,
                style = typography.smallText,
                color = color.grayScale
            )
            Text(
                text = article.title,
                style = typography.mediumText,
                color = color.black,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    model = R.drawable.ic_notification,
                    contentDescription = null,
                    modifier = Modifier.size(size = 20.dp)
                )
                Text(
                    text = article.source.name,
                    style = typography.xSmallText,
                    color = color.grayScale
                )
                GlideImage(
                    model = R.drawable.ic_time,
                    modifier = Modifier.size(size = 14.dp),
                    contentDescription = null
                )
                Text(
                    text = article.timePassed,
                    style = typography.xSmallText,
                    color = color.grayScale,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewTrendingNewsCard() {
    NewsTheme {
        TrendingNewsCard(
            article = NewsArticle(
                author = "Author",
                content = "Content",
                description = "Description",
                publishedAt = "2023-09-15T1",
                source = NewsSource(
                    id = "id",
                    name = "BBC"
                ),
                title = "Title",
                url = "https://www.google.com",
                urlToImage = "https://techcrunch.com/wp-content/uploads/2025/10/google-jules.jpg?resize=1200,800",
                timePassed = "90 days ago"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeTrendingList() {
    NewsTheme {

        HomeTrendingList(
            modifier = Modifier.fillMaxWidth(),
            news = null
        )
    }
}

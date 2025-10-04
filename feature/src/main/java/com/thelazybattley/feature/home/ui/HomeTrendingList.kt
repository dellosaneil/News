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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.ui.shimmerEffect
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.core.ui.theme.LocalNewsTypography
import com.thelazybattley.core.ui.theme.NewsTheme
import com.thelazybattley.feature.R
import com.thelazybattley.feature.home.provider.HomeTrendingNewsStateProvider
import com.thelazybattley.feature.home.state.HomeTrendingNewsState

@Composable
fun HomeTrendingList(
    modifier: Modifier = Modifier,
    articles: HomeTrendingNewsState
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
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            if (articles.isLoading) {
                items(5) {
                    TrendingCardShimmer()
                }
                return@LazyRow
            }
            items(items = articles.articles) { article ->
                TrendingNewsCard(
                    article = article
                )
            }
        }
    }
}

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
            val imageModifier = Modifier
                .clip(
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .height(150.dp)
                .fillMaxWidth()
            GlideImage(
                imageModel = { article.urlToImage },
                modifier = imageModifier,
                loading = {
                    Spacer(
                        modifier = Modifier
                            .shimmerEffect()
                            .then(imageModifier)
                    )
                },
                failure = {
                    Spacer(
                        modifier = Modifier
                            .shimmerEffect()
                            .then(imageModifier)
                    )
                }
            )
            Text(
                text = article.author,
                style = typography.smallText,
                color = color.grayScale,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
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
    }
}

@Composable
private fun TrendingCardShimmer(modifier: Modifier = Modifier) {
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
            Spacer(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .shimmerEffect()
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .shimmerEffect()
                    .size(width = 36.dp, height = 12.dp)
            )
            Spacer(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .shimmerEffect()
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Row(
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
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeTrendingList(
    @PreviewParameter(HomeTrendingNewsStateProvider::class) state: HomeTrendingNewsState
) {
    NewsTheme {
        HomeTrendingList(
            modifier = Modifier.fillMaxWidth(),
            articles = state,
        )
    }
}

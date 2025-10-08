package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.thelazybattley.feature.home.provider.HomeTrendingNewsStateProvider
import com.thelazybattley.feature.home.state.HomeArticlesState
import com.thelazybattley.feature.util.CommonSourceDetails
import com.thelazybattley.feature.util.ShimmerCommonSourceDetails

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
                color = color.grayScale,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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


@Composable
fun ShimmerNewsDetailsPreview(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
    ) {
        val imageModifier = Modifier
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .size(width = 120.dp, height = 100.dp)
        val shimmerModifier = Modifier
            .clip(
                shape = RoundedCornerShape(size = 8.dp)
            )
            .shimmerEffect()
        Spacer(
            modifier = shimmerModifier
                .then(
                    other = imageModifier
                )
        )
        Column(
            modifier = Modifier.weight(weight = 1f),
            verticalArrangement = Arrangement.spacedBy(space = 4.dp)
        ) {
            Spacer(
                modifier = shimmerModifier
                    .size(height = 16.dp, width = 56.dp)
            )
            Spacer(
                modifier = shimmerModifier
                    .height(height = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(
                modifier = shimmerModifier
                    .height(height = 16.dp)
                    .fillMaxWidth()
            )
            ShimmerCommonSourceDetails(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewShimmerNewsDetailsPreview() {
    ShimmerNewsDetailsPreview()
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewsDetailsPreview(
    @PreviewParameter(HomeTrendingNewsStateProvider::class) state: HomeArticlesState
) {
    NewsTheme {
        NewsDetailsPreview(
            modifier = Modifier.fillMaxWidth(),
            article = state.articles.first(),
        )
    }
}

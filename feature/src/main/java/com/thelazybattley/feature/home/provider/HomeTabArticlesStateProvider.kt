package com.thelazybattley.feature.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.network.data.news.NewsSource
import com.thelazybattley.core.util.TimeAgo
import com.thelazybattley.feature.home.state.HomeTabArticlesState

class HomeTabArticlesStateProvider : PreviewParameterProvider<HomeTabArticlesState> {
    override val values: Sequence<HomeTabArticlesState>
        get() = sequenceOf(
            HomeTabArticlesState(isLoading = true),
            HomeTabArticlesState(
                articles = listOf(
                    NewsArticle(
                        author = "Author",
                        content = "Content",
                        description = "Description",
                        publishedAt = "2023-09-15T1",
                        source = NewsSource(
                            id = "id",
                            name = "BBC",
                            imageUrl = "https://www.google.com/s2/favicons?domain=github.com"
                        ),
                        title = "Title",
                        url = "https://www.google.com",
                        urlToImage = "",
                        timePassed = TimeAgo.Days(value = 90)
                    ),
                    NewsArticle(
                        author = "Author",
                        content = "Content",
                        description = "Description",
                        publishedAt = "2023-09-15T1",
                        source = NewsSource(
                            id = "id",
                            name = "BBC",
                            imageUrl = "https://www.google.com/s2/favicons?domain=github.com"
                        ),
                        title = "Title",
                        url = "https://www.google.com",
                        urlToImage = "https://sportshub.cbsistatic.com/i/r/2025/09/30/502b0cbc-3743-45bd-9e2c-c0b7653b2b1e/thumbnail/1200x675/134de9ae08c980dab57d37946699f896/aaron-judge-new-york-yankees-imagn-images-8.jpg",
                        timePassed = TimeAgo.Days(value = 90)
                    )
                ),
                isLoading = false
            )
        )
}

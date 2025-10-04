package com.thelazybattley.feature.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.network.data.news.NewsSource
import com.thelazybattley.feature.home.HomeTrendingNewsState

class HomePreviewParameterProvider : PreviewParameterProvider<HomeTrendingNewsState> {
    override val values: Sequence<HomeTrendingNewsState>
        get() = sequenceOf(
            HomeTrendingNewsState(isLoading = true),
            HomeTrendingNewsState(
                articles = listOf(
                    NewsArticle(
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
                ),
                isLoading = false
            )
        )
}

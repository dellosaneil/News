package com.thelazybattley.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thelazybattley.feature.home.state.HomeArticlesState

@Composable
fun HomeLatestNewsList(
    modifier: Modifier = Modifier,
    articles: HomeArticlesState
) {
    
}

@Preview
@Composable
private fun PreviewHomeLatestNewsList() {
    HomeLatestNewsList(
        articles = HomeArticlesState(
            isError = false,
            isLoading = false,
            articles = emptyList()
        )
    )
}

package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.core.ui.theme.NewsTheme
import com.thelazybattley.feature.R
import com.thelazybattley.feature.home.HomeTabCallbacks
import com.thelazybattley.feature.home.HomeTabViewModel
import com.thelazybattley.feature.home.HomeTabViewModel.Companion.PAGE_SIZE
import com.thelazybattley.feature.home.state.HomeTabViewState
import com.thelazybattley.feature.util.CommonEmptyNews
import com.thelazybattley.feature.util.CommonNewsDetailsPreview
import com.thelazybattley.feature.util.CommonSearchBar
import com.thelazybattley.feature.util.CommonTopBar
import com.thelazybattley.feature.util.ShimmerCommonNewsDetailsPreview


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<HomeTabViewModel>()
    val viewState by viewModel.getViewState().collectAsStateWithLifecycle()
    HomeScreen(modifier = modifier, viewState = viewState, callbacks = viewModel)
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewState: HomeTabViewState,
    callbacks: HomeTabCallbacks
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CommonTopBar(
                modifier = Modifier.fillMaxWidth()
            )
        },
        containerColor = LocalNewsColors.current.white,
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            item {
                CommonSearchBar(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) { keyword ->
                    callbacks.onSearchKeyword(keyword = keyword)
                }
            }
            item {
                HomeTabTrendingHeader()
            }
            item {
                if (!viewState.trendingArticles.isLoading && viewState.trendingArticles.articles.isEmpty()) {
                    CommonEmptyNews(
                        modifier = Modifier,
                        title = stringResource(R.string.no_news_found),
                        subtext = stringResource(R.string.empty_news_subtext)

                    )
                } else {
                    HomeTabTrendingNewsList(
                        modifier = Modifier,
                        articles = viewState.trendingArticles
                    )
                }
            }
            stickyHeader {
                HomeTabLatestNewsHeader(
                    modifier = Modifier,
                    callbacks = callbacks
                )
            }
            if(!viewState.highlightedArticles.isLoading && viewState.highlightedArticles.articles.isEmpty()) {
                item {
                    CommonEmptyNews(
                        modifier = Modifier,
                        title = stringResource(R.string.no_news_found),
                        subtext = stringResource(R.string.empty_news_subtext)

                    )
                }
            }
            if (viewState.highlightedArticles.isLoading) {
                items(count = PAGE_SIZE) {
                    ShimmerCommonNewsDetailsPreview(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }
            }

            items(
                items = viewState.highlightedArticles.articles
            ) { article ->
                CommonNewsDetailsPreview(
                    article = article,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    NewsTheme {
        HomeScreen(
            modifier = Modifier, viewState = HomeTabViewState(),
            callbacks = HomeTabCallbacks.default()
        )
    }
}

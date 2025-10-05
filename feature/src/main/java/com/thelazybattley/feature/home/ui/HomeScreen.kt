package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.core.ui.theme.NewsTheme
import com.thelazybattley.feature.home.HomeViewModel
import com.thelazybattley.feature.home.state.HomeViewState
import com.thelazybattley.feature.util.CommonSearchBar


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val viewState by viewModel.getViewState().collectAsStateWithLifecycle()
    HomeScreen(modifier = modifier, viewState = viewState)
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewState: HomeViewState) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth()
            )
        },
        containerColor = LocalNewsColors.current.white,
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            item {
                CommonSearchBar(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                HomeTrendingList(
                    modifier = Modifier,
                    articles = viewState.trendingArticles
                )
            }
            stickyHeader {
                HomeStickyHeader(
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    NewsTheme {
        HomeScreen(modifier = Modifier, viewState = HomeViewState())
    }
}

package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
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
import com.thelazybattley.feature.home.HomeViewState
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
        modifier = modifier
            .padding(horizontal = 24.dp),
        topBar = {
            HomeScreenTopBar()
        },
        containerColor = LocalNewsColors.current.white,
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CommonSearchBar()
            }
            item {
                HomeTrendingList(
                    modifier = Modifier,
                    news = viewState.trendingNews
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

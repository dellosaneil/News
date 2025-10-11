package com.thelazybattley.feature.seeall.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.thelazybattley.feature.seeall.SeeAllViewModel
import timber.log.Timber

@Composable
fun SeeAllScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<SeeAllViewModel>()
    val viewState by viewModel.getViewState().collectAsStateWithLifecycle()
    val articles = viewState.newsArticles.collectAsLazyPagingItems()
    val loadState = articles.loadState
    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }
    when (loadState.refresh) {
        is LoadState.Error -> {
            Timber.d("Test: Error")
        }

        is LoadState.Loading -> {
            Timber.d("Test: Loading")
        }

        is LoadState.NotLoading -> {
            Timber.d("Test: Not Loading")
        }
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 26.dp)
    ) {
        items(
            count = articles.itemCount
        ) { index ->
            val item = articles[index]
            item?.let {
                Text(
                    text = it.url,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}

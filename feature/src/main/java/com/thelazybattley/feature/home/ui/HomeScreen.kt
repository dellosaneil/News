package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.thelazybattley.feature.home.HomeViewModel
import com.thelazybattley.feature.util.CommonSearchBar

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel : HomeViewModel = hiltViewModel()
    viewModel

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeScreenTopBar()
        }
    ) { contentPadding ->
        LazyColumn(contentPadding = contentPadding) {
            item {
                CommonSearchBar()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}

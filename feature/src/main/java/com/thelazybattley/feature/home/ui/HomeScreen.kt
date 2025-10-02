package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thelazybattley.feature.util.SearchBar

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeScreenTopBar()
        }
    ) { contentPadding ->
        LazyColumn(contentPadding = contentPadding) {
            item {
                SearchBar()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}

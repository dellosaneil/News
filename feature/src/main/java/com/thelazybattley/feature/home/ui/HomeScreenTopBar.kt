package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.feature.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        actions = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification), null,
                    modifier = Modifier.size(size = 24.dp)
                )
            }
        }, title = {}
    )

}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreenTopBar() {
    HomeScreenTopBar()
}

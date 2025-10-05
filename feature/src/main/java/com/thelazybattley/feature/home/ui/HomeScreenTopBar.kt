package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.feature.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(LocalNewsColors.current.white)
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = {

            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notification), null,
                modifier = Modifier.size(size = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreenTopBar() {
    HomeScreenTopBar()
}

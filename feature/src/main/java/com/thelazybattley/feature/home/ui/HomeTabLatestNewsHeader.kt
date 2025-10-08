package com.thelazybattley.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.core.ui.theme.LocalNewsTypography
import com.thelazybattley.core.ui.theme.NewsTheme
import com.thelazybattley.core.util.LatestNewsCategories
import com.thelazybattley.feature.R
import com.thelazybattley.feature.home.HomeTabCallbacks

@Composable
fun HomeTabLatestNewsHeader(
    modifier: Modifier = Modifier,
    callbacks: HomeTabCallbacks
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = LocalNewsColors.current.white)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.latest),
                style = LocalNewsTypography.current.mediumText,
                color = LocalNewsColors.current.black
            )
            Spacer(modifier = Modifier.weight(weight = 1f))
            Text(
                text = stringResource(R.string.see_all),
                style = LocalNewsTypography.current.smallText,
                color = LocalNewsColors.current.grayScale
            )
        }
        HomeLatestNewsCategoryTabs(
            modifier = Modifier.fillMaxWidth(),
            callbacks = callbacks
        )
    }
}

@Composable
private fun HomeLatestNewsCategoryTabs(modifier: Modifier = Modifier, callbacks: HomeTabCallbacks) {
    val colors = LocalNewsColors.current
    val startDestination = LatestNewsCategories.BUSINESS
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    PrimaryScrollableTabRow(
        selectedTabIndex = selectedDestination,
        modifier = modifier,
        edgePadding = 0.dp,
        containerColor = colors.white,
        divider = {},
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier.tabIndicatorOffset(
                    selectedTabIndex = selectedDestination,
                    matchContentSize = true
                ),
                width = Dp.Unspecified,
                color = colors.primary
            )
        }
    ) {
        LatestNewsCategories.entries.forEachIndexed { index, destination ->
            val isSelected = selectedDestination == index
            val textColor =
                if (isSelected) colors.black else colors.grayScale
            Tab(
                selected = isSelected,
                onClick = {
                    callbacks.onCategorySelected(category = destination)
                    selectedDestination = index
                },
                text = {
                    Text(
                        text = stringResource(id = destination.label),
                        style = LocalNewsTypography.current.mediumText,
                        color = textColor
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeTabLatestNewsHeader() {
    NewsTheme {
        HomeTabLatestNewsHeader(
            callbacks = HomeTabCallbacks.default()
        )
    }
}

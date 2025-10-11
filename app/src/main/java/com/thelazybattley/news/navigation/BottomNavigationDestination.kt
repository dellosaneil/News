package com.thelazybattley.news.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thelazybattley.news.R

enum class BottomNavigationDestination(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    HOME(route = "home", label = R.string.home, icon = R.drawable.ic_home),
    EXPLORE(route = "explore", label = R.string.explore, icon = R.drawable.ic_explore),
    BOOKMARK(route = "bookmark", label = R.string.bookmark, icon = R.drawable.ic_bookmark)
}

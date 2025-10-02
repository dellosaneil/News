package com.thelazybattley.news.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.thelazybattley.news.R

enum class Destination(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    HOME(route = "home", label = R.string.home, icon = R.drawable.ic_home),
    SEARCH(route = "search", label = R.string.search, icon = com.thelazybattley.core.R.drawable.ic_search),
    BOOKMARK(route = "bookmark", label = R.string.bookmark, icon = R.drawable.ic_bookmark)
}

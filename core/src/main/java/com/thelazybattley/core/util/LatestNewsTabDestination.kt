package com.thelazybattley.core.util

import androidx.annotation.StringRes
import com.thelazybattley.core.R

enum class LatestNewsTabDestination(val route: String, @StringRes val label: Int) {
    BUSINESS(route = "business", label = R.string.business),
    ENTERTAINMENT(route = "entertainment", label = R.string.entertainment),
    GENERAL(route = "general", label = R.string.general),
    HEALTH(route = "health", label = R.string.health),
    SCIENCE(route = "science", label = R.string.science),
    SPORTS(route = "sports", label = R.string.sports),
    TECHNOLOGY(route = "technology", label = R.string.technology),
}

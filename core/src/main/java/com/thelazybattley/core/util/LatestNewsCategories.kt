package com.thelazybattley.core.util

import androidx.annotation.StringRes
import com.thelazybattley.core.R

enum class LatestNewsCategories(val query: String, @StringRes val label: Int) {
    BUSINESS(query = "business", label = R.string.business),
    ENTERTAINMENT(query = "entertainment", label = R.string.entertainment),
    GENERAL(query = "general", label = R.string.general),
    HEALTH(query = "health", label = R.string.health),
    SCIENCE(query = "science", label = R.string.science),
    SPORTS(query = "sports", label = R.string.sports),
    TECHNOLOGY(query = "technology", label = R.string.technology),
}

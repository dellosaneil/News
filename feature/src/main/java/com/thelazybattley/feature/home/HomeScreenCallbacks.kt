package com.thelazybattley.feature.home

import com.thelazybattley.core.util.LatestNewsCategories

interface HomeScreenCallbacks {

    fun onCategorySelected(category: LatestNewsCategories)

    fun default() = object: HomeScreenCallbacks {
        override fun onCategorySelected(category: LatestNewsCategories) {
            TODO("Not yet implemented")
        }
    }
}



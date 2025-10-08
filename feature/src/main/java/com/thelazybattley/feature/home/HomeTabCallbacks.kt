package com.thelazybattley.feature.home

import com.thelazybattley.core.util.LatestNewsCategories

interface HomeTabCallbacks {

    fun onCategorySelected(category: LatestNewsCategories)

    fun onSearchKeyword(keyword: String)

    companion object {
        fun default() = object: HomeTabCallbacks {
            override fun onCategorySelected(category: LatestNewsCategories) {
                TODO("Not yet implemented")
            }

            override fun onSearchKeyword(keyword: String) {
                TODO("Not yet implemented")
            }
        }
    }
}



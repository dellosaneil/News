package com.thelazybattley.feature.home

import com.thelazybattley.core.util.LatestNewsCategories

interface HomeTabCallbacks {

    fun onCategorySelected(category: LatestNewsCategories)

    companion object {
        fun default() = object: HomeTabCallbacks {
            override fun onCategorySelected(category: LatestNewsCategories) {
                TODO("Not yet implemented")
            }

        }

    }
}



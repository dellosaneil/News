package com.thelazybattley.feature.seeall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.usecase.FetchNewsPagination
import com.thelazybattley.feature.seeall.state.SeeAllViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val fetchNewsPagination: FetchNewsPagination
) : ViewModel(), SeeAllCallback {

    companion object {
        const val PAGE_SIZE = 10

    }

    private val _viewState = MutableStateFlow(value = SeeAllViewState())

    fun getViewState() = _viewState

    fun fetchNews() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val flow = fetchNewsPagination(
                keyword = null,
                category = null,
                path = NetworkPath.TOP_HEADLINES,
                pageSize = PAGE_SIZE
            ).cachedIn(viewModelScope)

            _viewState.update { it.copy(newsArticles = flow, isLoading = false) }
        }
    }
}

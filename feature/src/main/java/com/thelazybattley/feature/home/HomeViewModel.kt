package com.thelazybattley.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.usecase.FetchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(value = HomeViewState())

    fun getViewState() = _viewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchTrendingNews()
        }
    }

    private suspend fun fetchTrendingNews() {
        fetchNewsUseCase(keyword = null, path = NetworkPath.TOP_HEADLINES, pageSize = 6).fold(
            onSuccess = { result ->
                _viewState.update { state ->
                    state.copy(
                        trendingArticles = HomeTrendingNewsState(
                            articles = result.articles,
                            isLoading = false,
                            isError = false
                        )
                    )
                }
            },
            onFailure = {
                _viewState.update { state ->
                    state.copy(
                        trendingArticles = HomeTrendingNewsState(
                            articles = emptyList(),
                            isLoading = false,
                            isError = false
                        )
                    )
                }
            }
        )
    }
}

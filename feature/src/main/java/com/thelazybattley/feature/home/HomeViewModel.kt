package com.thelazybattley.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.usecase.FetchNewsSourcesUseCase
import com.thelazybattley.core.network.usecase.FetchNewsUseCase
import com.thelazybattley.feature.home.state.HomeTrendingNewsState
import com.thelazybattley.feature.home.state.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
    private val fetchNewsSourcesUseCase: FetchNewsSourcesUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(value = HomeViewState())

    fun getViewState() = _viewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val sources = async {
                fetchNewsSource()
            }
            val trendingNews = async {
                fetchTrendingNews()
            }
            sources.await()
            trendingNews.await()
        }
    }

    private suspend fun fetchNewsSource() {
        fetchNewsSourcesUseCase().fold(
            onSuccess = { result ->
                _viewState.update { state ->
                    state.copy(
                        newsSources = state.newsSources.copy(
                            sources = result,
                            isLoading = false,
                            isError = false
                        )
                    )
                }
            },
            onFailure = {
                _viewState.update { state ->
                    state.copy(
                        newsSources = state.newsSources.copy(
                            isLoading = false,
                            isError = true
                        )
                    )
                }
            }
        )
    }

    private suspend fun fetchTrendingNews() {
        fetchNewsUseCase(keyword = null, path = NetworkPath.TOP_HEADLINES, pageSize = 5)
            .fold(
                onSuccess = { result ->
                    _viewState.update { state ->
                        state.copy(
                            trendingArticles = HomeTrendingNewsState(
                                articles = result.articles.map { article ->
                                    val imageUrl = state.newsSources.sources.find { it.id == article.source.id }?.imageUrl ?: ""
                                    article.copy(
                                        source = article.source.copy(
                                            imageUrl = imageUrl
                                        )
                                    )
                                },
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

package com.thelazybattley.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thelazybattley.core.db.usecase.GetNewsSourceDetailsUseCase
import com.thelazybattley.core.db.usecase.InsertNewsSourceDetailsUseCase
import com.thelazybattley.core.network.data.sources.NewsSourceDetails
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.usecase.FetchNewsSourcesUseCase
import com.thelazybattley.core.network.usecase.FetchNewsUseCase
import com.thelazybattley.feature.home.state.HomeArticlesState
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
    private val fetchNewsSourcesUseCase: FetchNewsSourcesUseCase,
    private val insertNewsSourceDetailsUseCase: InsertNewsSourceDetailsUseCase,
    private val getNewsSourceDetailsUseCase: GetNewsSourceDetailsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(value = HomeViewState())

    fun getViewState() = _viewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val sources = async {
                val newsSourceDetails = getNewsSourceDetails()
                if (newsSourceDetails.isEmpty()) {
                    newsSourceDetails.toMutableList()
                        .addAll(fetchNewsSourceDetails())
                    insertNewsSourceDetailsUseCase(newsSourceDetails)
                }
                updateNewsSourceState(newsSourceDetails = newsSourceDetails)
            }
            sources.await()
            val trendingNews = async {
                fetchTrendingNews()
            }
            trendingNews.await()
        }
    }

    private suspend fun fetchNewsSourceDetails(): List<NewsSourceDetails> {
        fetchNewsSourcesUseCase().fold(
            onSuccess = { result ->

                return result
            },
            onFailure = {
                _viewState.update { state ->
                    state.copy(
                        newsSources = state.newsSources.copy(
                            isError = true
                        )
                    )
                }
                return emptyList()
            }
        )

    }

    private suspend fun getNewsSourceDetails(): List<NewsSourceDetails> {
        return getNewsSourceDetailsUseCase().getOrNull() ?: emptyList()
    }

    private fun updateNewsSourceState(newsSourceDetails: List<NewsSourceDetails>) {
        _viewState.update { state ->
            state.copy(
                newsSources = state.newsSources.copy(
                    sources = newsSourceDetails,
                    isLoading = false
                )
            )
        }
    }

    private suspend fun fetchTrendingNews() {
        fetchNewsUseCase(
            keyword = null,
            path = NetworkPath.TOP_HEADLINES,
            pageSize = 5,
            category = null
        ).fold(
            onSuccess = { result ->
                _viewState.update { state ->
                    state.copy(
                        trendingArticles = HomeArticlesState(
                            articles = result.articles.map { article ->
                                val imageUrl =
                                    state.newsSources.sources.find { it.id == article.source.id }?.imageUrl
                                        ?: ""
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
                        trendingArticles = HomeArticlesState(
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

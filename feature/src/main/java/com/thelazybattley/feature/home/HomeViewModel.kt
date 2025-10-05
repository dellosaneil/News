package com.thelazybattley.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thelazybattley.core.db.usecase.GetNewsSourceDetailsUseCase
import com.thelazybattley.core.db.usecase.InsertNewsSourceDetailsUseCase
import com.thelazybattley.core.network.data.news.NewsArticle
import com.thelazybattley.core.network.data.sources.NewsSourceDetails
import com.thelazybattley.core.network.enums.NetworkPath
import com.thelazybattley.core.network.usecase.FetchNewsSourcesUseCase
import com.thelazybattley.core.network.usecase.FetchNewsUseCase
import com.thelazybattley.core.util.LatestNewsCategories
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
) : ViewModel(), HomeScreenCallbacks {

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
                fetchNews(category = null)
            }
            val latestNews = async {
                fetchNews(category = getViewState().value.categorySelected)
            }
            latestNews.await()
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

    private fun updateArticleList(articles: List<NewsArticle>, category: LatestNewsCategories?) {
        _viewState.update { state ->
            val homeArticlesState = HomeArticlesState(
                articles = articles.map { article ->
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
            when (category) {
                LatestNewsCategories.BUSINESS -> state.copy(businessArticles = homeArticlesState)
                LatestNewsCategories.ENTERTAINMENT -> state.copy(entertainmentArticles = homeArticlesState)
                LatestNewsCategories.GENERAL -> state.copy(generalArticles = homeArticlesState)
                LatestNewsCategories.HEALTH -> state.copy(healthArticles = homeArticlesState)
                LatestNewsCategories.SCIENCE -> state.copy(scienceArticles = homeArticlesState)
                LatestNewsCategories.SPORTS -> state.copy(sportsArticles = homeArticlesState)
                LatestNewsCategories.TECHNOLOGY -> state.copy(technologyArticles = homeArticlesState)
                null -> state.copy(trendingArticles = homeArticlesState)
            }
        }
        _viewState.update { state ->
            state.copy(
                highlightedArticles = articles
            )
        }
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

    private suspend fun fetchNews(category: LatestNewsCategories?) {
        fetchNewsUseCase(
            keyword = null,
            path = NetworkPath.TOP_HEADLINES,
            pageSize = 5,
            category = category
        ).fold(
            onSuccess = { result ->
                updateArticleList(articles = result.articles, category = category)
            },
            onFailure = {
                _viewState.update { state ->
                    val homeArticlesState = HomeArticlesState(
                        articles = emptyList(),
                        isLoading = false,
                        isError = false
                    )
                    when (category) {
                        LatestNewsCategories.BUSINESS -> state.copy(businessArticles = homeArticlesState)
                        LatestNewsCategories.ENTERTAINMENT -> state.copy(entertainmentArticles = homeArticlesState)
                        LatestNewsCategories.GENERAL -> state.copy(generalArticles = homeArticlesState)
                        LatestNewsCategories.HEALTH -> state.copy(healthArticles = homeArticlesState)
                        LatestNewsCategories.SCIENCE -> state.copy(scienceArticles = homeArticlesState)
                        LatestNewsCategories.SPORTS -> state.copy(sportsArticles = homeArticlesState)
                        LatestNewsCategories.TECHNOLOGY -> state.copy(technologyArticles = homeArticlesState)
                        null -> state.copy(trendingArticles = homeArticlesState)
                    }
                }
            }
        )
    }

    override fun onCategorySelected(category: LatestNewsCategories) {
        _viewState.update { state ->
            state.copy(
                categorySelected = category
            )
        }
        viewModelScope.launch(context = Dispatchers.IO) {
            fetchNews(category = category)
        }
    }
}

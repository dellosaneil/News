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
import com.thelazybattley.feature.home.state.HomeTabArticlesState
import com.thelazybattley.feature.home.state.HomeTabViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTabViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
    private val fetchNewsSourcesUseCase: FetchNewsSourcesUseCase,
    private val insertNewsSourceDetailsUseCase: InsertNewsSourceDetailsUseCase,
    private val getNewsSourceDetailsUseCase: GetNewsSourceDetailsUseCase
) : ViewModel(), HomeTabCallbacks {

    companion object {
        const val PAGE_SIZE = 10
    }

    private val _viewState = MutableStateFlow(value = HomeTabViewState())

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
                fetchNews(category = LatestNewsCategories.BUSINESS)
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
        if (category != null) {
            _viewState.update { state ->
                state.copy(
                    highlightedArticles = HomeTabArticlesState(
                        articles = articles,
                        isLoading = false,
                        isError = false
                    )
                )
            }
        }
        val homeTabArticlesState = HomeTabArticlesState(
            articles = articles.map { article ->
                val imageUrl =
                    _viewState.value.newsSources.sources.find { it.id == article.source.id }?.imageUrl
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
        updateArticleByCategory(homeTabArticlesState = homeTabArticlesState, category = category)

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
        updateArticleByCategory(
            category = category,
            homeTabArticlesState = HomeTabArticlesState(
                articles = emptyList(),
                isLoading = true,
                isError = false
            )
        )
        fetchNewsUseCase(
            keyword = _viewState.value.keyword,
            path = NetworkPath.TOP_HEADLINES,
            pageSize = PAGE_SIZE,
            category = category,
            page = 1,
        ).fold(
            onSuccess = { result ->
                updateArticleList(articles = result.articles, category = category)
            },
            onFailure = {
                val homeTabArticlesState = HomeTabArticlesState(
                    articles = emptyList(),
                    isLoading = false,
                    isError = false
                )
                updateArticleByCategory(
                    homeTabArticlesState = homeTabArticlesState,
                    category = category
                )
            }
        )
    }

    private fun updateArticleByCategory(
        category: LatestNewsCategories?,
        homeTabArticlesState: HomeTabArticlesState
    ) {
        _viewState.update { state ->
            when (category) {
                LatestNewsCategories.BUSINESS -> state.copy(businessArticles = homeTabArticlesState)
                LatestNewsCategories.ENTERTAINMENT -> state.copy(entertainmentArticles = homeTabArticlesState)
                LatestNewsCategories.GENERAL -> state.copy(generalArticles = homeTabArticlesState)
                LatestNewsCategories.HEALTH -> state.copy(healthArticles = homeTabArticlesState)
                LatestNewsCategories.SCIENCE -> state.copy(scienceArticles = homeTabArticlesState)
                LatestNewsCategories.SPORTS -> state.copy(sportsArticles = homeTabArticlesState)
                LatestNewsCategories.TECHNOLOGY -> state.copy(technologyArticles = homeTabArticlesState)
                null -> state.copy(trendingArticles = homeTabArticlesState)
            }
        }
    }

    override fun onCategorySelected(category: LatestNewsCategories) {
        val articles = articlesForCategory(category = category)
        _viewState.update { state ->
            state.copy(
                highlightedArticles = HomeTabArticlesState(
                    articles = articles,
                    isLoading = false,
                    isError = false
                ),
                categoryTabSelected = category
            )
        }
        if (articles.isNotEmpty()) return
        _viewState.update { state ->
            state.copy(
                highlightedArticles = HomeTabArticlesState(
                    articles = emptyList(),
                    isLoading = true,
                    isError = false
                )
            )
        }
        viewModelScope.launch(context = Dispatchers.IO) {
            fetchNews(category = category)
        }
    }

    private fun articlesForCategory(category: LatestNewsCategories): List<NewsArticle> {
        return when (category) {
            LatestNewsCategories.BUSINESS -> _viewState.value.businessArticles.articles
            LatestNewsCategories.ENTERTAINMENT -> _viewState.value.entertainmentArticles.articles
            LatestNewsCategories.GENERAL -> _viewState.value.generalArticles.articles
            LatestNewsCategories.HEALTH -> _viewState.value.healthArticles.articles
            LatestNewsCategories.SCIENCE -> _viewState.value.scienceArticles.articles
            LatestNewsCategories.SPORTS -> _viewState.value.sportsArticles.articles
            LatestNewsCategories.TECHNOLOGY -> _viewState.value.technologyArticles.articles
        }
    }

    override fun onSearchKeyword(keyword: String) {
        if (keyword == (_viewState.value.keyword ?: "")) {
            return
        }
        _viewState.update { state ->
            val defaultState = HomeTabArticlesState()
            state.copy(
                keyword = keyword.ifEmpty { null },
                businessArticles = defaultState,
                entertainmentArticles = defaultState,
                generalArticles = defaultState,
                healthArticles = defaultState,
                scienceArticles = defaultState,
                sportsArticles = defaultState,
                technologyArticles = defaultState,
                trendingArticles = defaultState
            )
        }
        viewModelScope.launch {
            launch {
                fetchNews(category = null)
                fetchNews(category = _viewState.value.categoryTabSelected)
            }
        }
    }
}

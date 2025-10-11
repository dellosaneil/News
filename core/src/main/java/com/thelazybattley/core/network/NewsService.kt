package com.thelazybattley.core.network

import com.thelazybattley.core.network.response.news.NewsResponse
import com.thelazybattley.core.network.response.sources.NewsSourcesDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("{path}")
    suspend fun fetchNews(
        @Path("path") path: String,
        @Query("q") keyword: String?,
        @Query("pageSize") pageSize: Int,
        @Query("country") country: String = "US",
        @Query("category") category: String?,
        @Query("page") page: Int
    ): NewsResponse

    @GET("top-headlines/sources")
    suspend fun fetchNewsSources(): NewsSourcesDetailsResponse

}

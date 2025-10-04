package com.thelazybattley.core.network

import com.thelazybattley.core.network.response.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("{path}")
    suspend fun fetchNews(
        @Path("path") path: String,
        @Query("q") keyword: String?,
        @Query("pageSize") pageSize: Int,
        @Query("country") country: String = "US"
    ): NewsResponse

}

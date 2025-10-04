package com.thelazybattley.core.network

import com.thelazybattley.core.network.response.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("{path}")
    suspend fun fetchNews(
        @Query("q") keyword: String?,
        @Path("path") path: String
    ): NewsResponse

}

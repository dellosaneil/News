package com.thelazybattley.core.network

import com.thelazybattley.core.network.response.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun fetchNewsByKeyword(@Query("q") keyword: String): NewsResponse

}

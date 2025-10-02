package com.thelazybattley.core.network

import com.thelazybattley.core.network.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun searchNews(@Query("q") searchTerm: String) : NewsResponse

}

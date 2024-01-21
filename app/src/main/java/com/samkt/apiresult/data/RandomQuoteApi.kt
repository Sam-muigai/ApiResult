package com.samkt.apiresult.data

import com.samkt.apiresult.data.model.RandomQuote
import retrofit2.http.GET

interface RandomQuoteApi {
    @GET("random")
    suspend fun getRandomQuote(): RandomQuote

    companion object {
        const val BASE_URL = "https://api.quotable.io/"
    }
}

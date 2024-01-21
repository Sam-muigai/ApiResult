package com.samkt.apiresult.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RandomQuoteApiClient {

    fun randomQuoteApi(): RandomQuoteApi {
        return Retrofit.Builder()
            .baseUrl(RandomQuoteApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomQuoteApi::class.java)
    }
}

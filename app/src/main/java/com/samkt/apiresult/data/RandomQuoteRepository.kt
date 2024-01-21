package com.samkt.apiresult.data

import com.samkt.apiResult.ApiResult
import com.samkt.apiresult.data.model.RandomQuote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RandomQuoteRepository {
    private val randomQuoteApi = RandomQuoteApiClient.randomQuoteApi()

    fun getRandomQuote(): Flow<ApiResult<RandomQuote>> = flow {
        try {
            val quote = randomQuoteApi.getRandomQuote()
            emit(ApiResult.Success(quote))
        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }
}

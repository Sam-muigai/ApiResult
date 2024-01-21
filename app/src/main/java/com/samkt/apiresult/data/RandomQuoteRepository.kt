package com.samkt.apiresult.data

import com.samkt.apiResult.ApiResult
import com.samkt.apiResult.handleApiCall
import com.samkt.apiresult.data.model.RandomQuote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RandomQuoteRepository {
    private val randomQuoteApi = RandomQuoteApiClient.randomQuoteApi()

    fun getRandomQuote(): Flow<ApiResult<RandomQuote>> = flow {
        emit(handleApiCall { randomQuoteApi.getRandomQuote() })
    }
}

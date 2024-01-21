package com.samkt.apiresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.apiresult.data.RandomQuoteRepository
import com.samkt.apiresult.data.model.RandomQuote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val repository = RandomQuoteRepository()

    private val _mainScreenState = MutableStateFlow(MainScreenState())
    val mainScreenState = _mainScreenState.asStateFlow()

    init {
        getRandomQuote()
    }

    private fun getRandomQuote() {
        _mainScreenState.update {
            it.copy(
                loading = true,
            )
        }
        repository.getRandomQuote().onEach { apiResult ->
            apiResult.onSuccess { randomQuote ->
                _mainScreenState.update {
                    it.copy(
                        loading = false,
                        quote = randomQuote,
                    )
                }
            }.onError { exception ->
                _mainScreenState.update {
                    it.copy(
                        loading = false,
                        errorMessage = exception.localizedMessage,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class MainScreenState(
    val loading: Boolean = false,
    val quote: RandomQuote? = null,
    val errorMessage: String? = null,
)

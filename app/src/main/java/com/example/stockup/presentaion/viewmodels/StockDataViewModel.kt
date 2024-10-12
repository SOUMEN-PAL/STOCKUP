package com.example.stockup.presentaion.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.stockup.domain.models.stockQuote.StockQuoteModel
import com.example.stockup.domain.repository.StockRepository
import com.example.stockup.utils.StockQuoteState
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okio.IOException

class StockDataViewModel(private val repository: StockRepository) : ViewModel() {

    /*
    Segregated this view model from another StockViewModel to have an individual instance of the
     Stock data
    */
    private var _symbol by mutableStateOf("")
    private var _exchange by mutableStateOf("")

    private var _stockQuoteState = MutableStateFlow<StockQuoteState>(StockQuoteState.Loading())
    val stockQuoteState = _stockQuoteState.asStateFlow()


    fun updateDataParameters(symbol: String, exchange: String) {
        _symbol = symbol
        _exchange = exchange
    }

    var dataJob: Job? = null

    fun resetState() {
        _stockQuoteState.value = StockQuoteState.Loading()
        dataJob?.cancel()
    }

    fun GetStockData() {
        dataJob?.cancel()
        dataJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            try {
                repository.getStockData(symbol = _symbol, exchangeName = _exchange)

                if(repository.stockQuoteData.value == null && repository.errorStateData.value == null) {
                    _stockQuoteState.value = StockQuoteState.Error("Invalid Information")
                }
                else if (repository.errorStateData.value == null) {
                    _stockQuoteState.value =
                        StockQuoteState.Success(repository.stockQuoteData.value)
                } else if (repository.searchedStockData.value == null) {
                    _stockQuoteState.value = StockQuoteState.DataNotAvailable()
                }
            } catch (e: IOException) {
                // Handle network errors
                _stockQuoteState.value = StockQuoteState.Error("Network error: ${e.message}")
            } catch (e: HttpException) {
                // Handle HTTP errors
                _stockQuoteState.value = StockQuoteState.Error("HTTP error: ${e.message}")
            } catch (e: JsonSyntaxException) {
                // Handle parsing errors
                _stockQuoteState.value = StockQuoteState.Error("Parsing error: ${e.message}")
            } catch (e: Exception) {
                // Handle any other exceptions
                _stockQuoteState.value =
                    StockQuoteState.Error("An unexpected error occurred: ${e.message}")
            }
        }
    }
}
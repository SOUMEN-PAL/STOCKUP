package com.example.stockup.presentaion.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockup.domain.models.stockQuote.StockQuoteModel
import com.example.stockup.domain.repository.StockRepository
import com.example.stockup.utils.StockQuoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockDataViewModel(private val repository: StockRepository):ViewModel() {

    /*
    Segregated this view model from another StockViewModel to have an individual instance of the
     Stock data
    */
    private var _symbol by mutableStateOf("")
    private var _exchange by mutableStateOf("")

    private var _stockQuoteState = MutableStateFlow<StockQuoteState>(StockQuoteState.Loading())
    val stockQuoteState = _stockQuoteState.asStateFlow()




    fun updateDataParameters(symbol : String , exchange : String){
        _symbol = symbol
        _exchange = exchange
    }
    var dataJob : Job? = null

    fun resetState(){
        _stockQuoteState.value = StockQuoteState.Loading()
        dataJob?.cancel()
    }

    fun GetStockData(){
        dataJob?.cancel()
        dataJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            try {
                repository.getStockData(symbol = _symbol , exchangeName = _exchange)
                val stockQuoteData = repository.stockQuoteData.value
                _stockQuoteState.value = if (stockQuoteData != null) {
                    StockQuoteState.Success(stockQuoteData)
                } else {
                    StockQuoteState.DataNotAvailable() // Set state to DataNotAvailable if data is null for and individual stock
                }

            }catch (e : Exception){
                _stockQuoteState.value = StockQuoteState.Error("Few Features may not be Available because of FREE API${e.message}")
            }
        }
    }
}
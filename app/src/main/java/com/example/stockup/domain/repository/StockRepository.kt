package com.example.stockup.domain.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.stockup.data.api.StockService
import com.example.stockup.domain.models.stockListings.StockListData

import com.example.stockup.domain.models.stockQuote.ErrorModel
import com.example.stockup.domain.models.stockQuote.StockQuoteModel
import com.example.stockup.domain.models.stockSearching.StockSearchData

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class StockRepository(
    private val stockService: StockService,
    private val context: Context
) {
    private val _stockList = MutableStateFlow<List<StockListData>?>(null)
    private val _searchedStockList = MutableStateFlow<List<StockSearchData>?>(null)
    private val _stockQuoteData = mutableStateOf<StockQuoteModel?>(null)
    private val _errorStateData = mutableStateOf<ErrorModel?>(null)

    //Exposing the private variables as read only,s
    val stockList = _stockList.asStateFlow()
    val searchedStockData = _searchedStockList.asStateFlow()
    val stockQuoteData = _stockQuoteData
    val errorStateData = _errorStateData


    suspend fun getStocks(exchangeName: String) {
        val response = stockService.getStocks(exchangeName)
        if (response.isSuccessful) {
            _stockList.value = response.body()?.data
        }
    }

    suspend fun searchStocks(symbol: String) {
        val response = stockService.searchSymbol(symbol)
        if (response.isSuccessful) {
            _searchedStockList.value = response.body()?.data
        }
    }

    suspend fun getStockData(symbol: String, exchangeName: String) {
        val response = stockService.getStockData(symbol = symbol, exchange = exchangeName)
        if (response.isSuccessful) {
            val responseBody = response.body()?.string()
            val gson = Gson()

            try {
                val stockQuote = gson.fromJson(responseBody, StockQuoteModel::class.java)
                _stockQuoteData.value = stockQuote

            } catch (e: JsonSyntaxException) {
                try {
                    val errorModel = gson.fromJson(responseBody, ErrorModel::class.java)
                    _errorStateData.value = errorModel
                } catch (errorParsingException: JsonSyntaxException) {
                    Log.e(
                        "StockRepository",
                        "Failed to parse response: ${errorParsingException.message}"
                    )
                }
            }
        } else {
            Log.e("StockRepository", "Network Error: ${response.code()}")
        }
    }
}
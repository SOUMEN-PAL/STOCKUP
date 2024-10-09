package com.example.stockup.domain.repository

import android.content.Context
import com.example.stockup.data.api.StockService
import com.example.stockup.domain.models.stockListings.StockListData
import com.example.stockup.domain.models.stockListings.StockListModel
import com.example.stockup.domain.models.stockQuote.StockQuoteModel
import com.example.stockup.domain.models.stockSearching.StockSearchData
import com.example.stockup.utils.StockListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.Flow

class StockRepository(
    private val stockService: StockService,
    private val context: Context
) {
    private val _stockList = MutableStateFlow<List<StockListData>?>(null)
    private val _searchedStockList = MutableStateFlow<List<StockSearchData>?>(null)
    private val _stockQuoteData = MutableStateFlow<StockQuoteModel?>(null)
    val stockList = _stockList.asStateFlow()
    val searchedStockData = _searchedStockList.asStateFlow()
    val stockQuoteData = _stockQuoteData.asStateFlow()



    suspend fun getStocks(exchangeName : String){
        val response = stockService.getStocks(exchangeName)
        if(response.isSuccessful){
            _stockList.value = response.body()?.data
        }
    }

    suspend fun searchStocks(symbol : String){
        val response = stockService.searchSymbol(symbol)
        if(response.isSuccessful){
            _searchedStockList.value = response.body()?.data
        }
    }

    suspend fun getStockData(symbol : String , exchangeName: String){
        val response = stockService.getStockData(symbol = symbol , exchange = exchangeName)
        if(response.isSuccessful){
            _stockQuoteData.value = response.body()
        }
    }
}
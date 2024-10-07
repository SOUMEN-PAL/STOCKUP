package com.example.stockup.presentaion.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockup.domain.models.stockListings.StockListData
import com.example.stockup.domain.repository.StockRepository
import com.example.stockup.utils.StockListState
import com.example.stockup.utils.StockPageState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class StocksViewModel(private val repository: StockRepository ) : ViewModel() {
    private val _StockListState = MutableStateFlow<StockListState>(StockListState.Loading())
    val stockListState = _StockListState.asStateFlow()

    private val _SearchedStockList = MutableStateFlow<StockListState>(StockListState.Loading())
    val SearchedStockListState = _SearchedStockList.asStateFlow()

    val exchange = mutableStateOf("BSE")
    val searchQuery = mutableStateOf("")
    val isSearching = mutableStateOf(false)
    val isRefreshed = mutableStateOf(false)


    init {
        getStocksListings()
    }

    fun getStocksListings(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getStocks(exchange.value)
                _StockListState.value = StockListState.Success<List<StockListData?>>(repository.stockList.value)
            }catch (e : Exception){
                _StockListState.value = StockListState.Error(e.toString())
            }
        }
    }

    fun getSearchedStocks(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.searchStocks(searchQuery.value)
                _SearchedStockList.value = StockListState.Success(repository.searchedStockData.value)

            }
            catch (e : Exception){
                _StockListState.value = StockListState.Error(e.toString())
            }
        }
    }

    private var searchJob : Job? = null

    fun onEvent(event : StockPageState){
        when(event){
            is StockPageState.OnSearchQuery -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getSearchedStocks()
                    isSearching.value = false
                }

            }
            is StockPageState.Refresh -> {
                getStocksListings()
                isRefreshed.value = false
            }
        }
    }

}
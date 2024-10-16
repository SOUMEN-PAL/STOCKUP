package com.example.stockup.presentaion.viewmodels


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockup.domain.models.stockListings.StockListData
import com.example.stockup.domain.repository.StockRepository
import com.example.stockup.utils.StockListState
import com.example.stockup.utils.StockPageState
import com.example.stockup.utils.StockQuoteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.log


class StocksViewModel(private val repository: StockRepository) : ViewModel() {
    private var _StockListState = MutableStateFlow<StockListState>(StockListState.Loading())
    val stockListState = _StockListState.asStateFlow()

    private var _SearchedStockList = MutableStateFlow<StockListState>(StockListState.Loading())
    val SearchedStockListState = _SearchedStockList.asStateFlow()



    private val exchange = mutableStateOf("NYSE")//By default set to NYSE
    val searchQuery = mutableStateOf("")
    val isSearching = mutableStateOf(false)
    val isRefreshed = mutableStateOf(false)


    init {
        //Fetching the initial list for the home screen
        getStocksListings()
    }


    fun resetSearchStockList() {
        _SearchedStockList.value = StockListState.Loading()
    }
    fun resetStockList(){
        _StockListState.value = StockListState.Loading()
    }

    fun getStocksListings() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getStocks(exchange.value)
                _StockListState.value =
                    StockListState.Success<List<StockListData?>>(repository.stockList.value)
            } catch (e: Exception) {
                _StockListState.value = StockListState.Error(e.toString())
            }
        }
    }

    fun getSearchedStocks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.searchStocks(searchQuery.value)
                if(repository.searchedStockData.value!!.isEmpty()){
                    Log.d("data invalid" , "Data empty")
                    _SearchedStockList.value = StockListState.DataInvalid()
                }
                else {
                    _SearchedStockList.value =
                        StockListState.Success(repository.searchedStockData.value)
                }
            } catch (e: Exception) {
                _StockListState.value = StockListState.Error(e.toString())
            }
        }
    }
    //Functions fetching needed symbol and exchange from the private viewmodel variables no exposer of provate variables


    private var searchJob: Job? = null

    //this function required to monitor the search and monitor searching and refreshing states
    fun onEvent(event: StockPageState) {
        when (event) {
            is StockPageState.OnSearchQuery -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L) // Debounce
                    getSearchedStocks()
                }
            }

            is StockPageState.Refresh -> {
                getStocksListings()
                Log.d("Refresh", "Yes StockList refreshed")
                isRefreshed.value = false

            }
        }
    }




}
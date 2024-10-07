package com.example.stockup.utils

sealed class StockListState{
    class Loading():StockListState()
    class Success<T>(val data : T?) : StockListState()
    class Error(val errorMessage: String) : StockListState()
}
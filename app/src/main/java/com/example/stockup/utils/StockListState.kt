package com.example.stockup.utils

sealed class StockListState{
    class Loading():StockListState()
    class Success<T>(data : T?) : StockListState()
    class Error<T>(errorMessage: String?) : StockListState()
}
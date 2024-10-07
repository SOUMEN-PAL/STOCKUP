package com.example.stockup.utils

sealed class StockListState<T>(val data: T? = null ,  val errorMessage : String? = null) {
    class Loading<T>():StockListState<T>(null , null)
    class Success<T>(data : T?) : StockListState<T>(data)
    class Error<T>(errorMessage: String?) : StockListState<T>(null  , errorMessage)
}
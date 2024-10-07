package com.example.stockup.utils

sealed class StockPageState {
    class Refresh : StockPageState()
    class OnSearchQuery(val query : String) :StockPageState()
}
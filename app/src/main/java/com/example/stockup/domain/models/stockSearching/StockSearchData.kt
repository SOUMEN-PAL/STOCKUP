package com.example.stockup.domain.models.stockSearching

data class StockSearchData(
    val country: String,
    val currency: String,
    val exchange: String,
    val exchange_timezone: String,
    val instrument_name: String,
    val instrument_type: String,
    val mic_code: String,
    val symbol: String
)
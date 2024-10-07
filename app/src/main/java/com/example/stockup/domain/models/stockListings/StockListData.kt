package com.example.stockup.domain.models.stockListings

data class StockListData(
    val country: String,
    val currency: String,
    val exchange: String,
    val figi_code: String,
    val mic_code: String,
    val name: String,
    val symbol: String,
    val type: String
)
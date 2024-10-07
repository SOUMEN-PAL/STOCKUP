package com.example.stockup.domain.models.stockListings

data class StockListModel(
    val count: Int,
    val `data`: List<StockListData>,
    val status: String
)
package com.example.stockup.data.api

import com.example.stockup.domain.models.stockListings.StockListModel
import com.example.stockup.domain.models.stockSearching.StockSearchingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {

    @GET("stocks")
    suspend fun getStocks(
        @Query("exchange") exchange : String
    ):Response<StockListModel>

    @GET("symbol_search")
    suspend fun searchSymbol(
        @Query("symbol") symbol : String
    ):Response<StockSearchingModel>

}
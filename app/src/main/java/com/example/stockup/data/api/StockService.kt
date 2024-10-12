package com.example.stockup.data.api

import com.example.stockup.domain.models.stockListings.StockListModel
import com.example.stockup.domain.models.stockQuote.StockQuoteModel
import com.example.stockup.domain.models.stockSearching.StockSearchingModel
import okhttp3.ResponseBody
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

    @GET("quote")
    suspend fun getStockData(
        @Query("symbol") symbol : String,
        @Query("apikey") apikey : String = RetrofitHelper.API_KEY,
        @Query("exchange") exchange: String
    ):Response<ResponseBody>

}
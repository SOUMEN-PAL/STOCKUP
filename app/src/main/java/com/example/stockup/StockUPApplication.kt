package com.example.stockup

import android.app.Application
import com.example.stockup.data.api.RetrofitHelper
import com.example.stockup.data.api.StockService

class StockUPApplication:Application() {
    override fun onCreate() {
        initialize()
        super.onCreate()
    }

    private fun initialize(){
        val stockService : StockService = RetrofitHelper.getInstance().create(StockService::class.java)
        //TODO:It create and init the stockRepository
    }
}
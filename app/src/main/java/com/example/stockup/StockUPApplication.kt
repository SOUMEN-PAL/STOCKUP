package com.example.stockup

import android.app.Application
import com.example.stockup.data.api.RetrofitHelper
import com.example.stockup.data.api.StockService
import com.example.stockup.domain.repository.StockRepository

class StockUPApplication:Application() {
    lateinit var repository : StockRepository
    override fun onCreate() {
        initialize()
        super.onCreate()
    }

    //Ensured separation of concern by separating the initialization of the repository from mainActivity to Application context
    private fun initialize(){
        val stockService : StockService = RetrofitHelper.getInstance().create(StockService::class.java)
        repository = StockRepository(stockService , applicationContext)
    }
}
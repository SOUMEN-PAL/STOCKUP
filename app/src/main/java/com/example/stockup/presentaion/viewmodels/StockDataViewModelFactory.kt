package com.example.stockup.presentaion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stockup.domain.repository.StockRepository

class StockDataViewModelFactory(private val repository: StockRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StockDataViewModel(repository) as T
    }
}
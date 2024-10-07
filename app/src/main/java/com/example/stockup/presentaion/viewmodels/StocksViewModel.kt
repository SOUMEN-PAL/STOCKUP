package com.example.stockup.presentaion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockup.domain.repository.StockRepository
import com.example.stockup.utils.StockListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StocksViewModel(private val repository: StockRepository ) : ViewModel() {
    private val _StockListState = MutableStateFlow<StockListState>(StockListState.Loading())
    val stockListState = _StockListState.asStateFlow()

    init {

    }
}
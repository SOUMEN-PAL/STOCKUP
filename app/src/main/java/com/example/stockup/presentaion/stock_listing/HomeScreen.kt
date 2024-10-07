package com.example.stockup.presentaion.stock_listing

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.stockup.domain.models.stockListings.StockListData
import com.example.stockup.presentaion.viewmodels.StocksViewModel
import com.example.stockup.utils.StockListState

@Composable
fun HomeScreen(modifier: Modifier = Modifier , viewModel: StocksViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val stockListState by viewModel.stockListState.collectAsStateWithLifecycle()
        var stockList : List<Any> = emptyList()
        when(stockListState){
            is StockListState.Error -> Text("Error: ${(stockListState as StockListState.Error).errorMessage}" , Modifier.padding(12.dp))
            is StockListState.Loading -> CircularProgressIndicator()
            is StockListState.Success<*> -> stockList = (stockListState as StockListState.Success<*>).data as List<StockListData>
        }


        LazyColumn {
            items(stockList){
                StockItem(stockListData = it as StockListData)
            }
        }
    }
}
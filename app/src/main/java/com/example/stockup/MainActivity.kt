package com.example.stockup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockup.presentaion.navigation.Navigation
import com.example.stockup.presentaion.stock_listing.HomeScreen
import com.example.stockup.presentaion.viewmodels.StockDataViewModel
import com.example.stockup.presentaion.viewmodels.StockDataViewModelFactory
import com.example.stockup.presentaion.viewmodels.StockViewModelFactory
import com.example.stockup.presentaion.viewmodels.StocksViewModel
import com.example.stockup.ui.theme.StockUPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val repository = (application as StockUPApplication).repository
            val stocksViewModel: StocksViewModel =
                viewModel(factory = StockViewModelFactory(repository))
            val stockDataViewModel: StockDataViewModel =
                viewModel(factory = StockDataViewModelFactory(repository))
            StockUPTheme {
                Navigation(
                    viewModel = stocksViewModel,
                    stockDataViewModel = stockDataViewModel,
                    context = this@MainActivity
                )
            }
        }
    }
}


package com.example.stockup.presentaion.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockup.presentaion.stock_info.StockDataScreen
import com.example.stockup.presentaion.stock_listing.HomeScreen
import com.example.stockup.presentaion.viewmodels.StockDataViewModel
import com.example.stockup.presentaion.viewmodels.StocksViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    viewModel: StocksViewModel,
    stockDataViewModel: StockDataViewModel,
    context: Context
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.homeScreen.route ){
        composable(route = Screens.homeScreen.route){
            HomeScreen(viewModel = viewModel , stockDataViewModel = stockDataViewModel , navController = navController)
        }
        composable(route = Screens.stockDataScreen.route){
            StockDataScreen(viewModel = stockDataViewModel , navController = navController)
        }
    }
}
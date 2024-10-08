package com.example.stockup.presentaion.stock_listing

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.stockup.domain.models.stockListings.StockListData
import com.example.stockup.domain.models.stockSearching.StockSearchData
import com.example.stockup.presentaion.viewmodels.StocksViewModel
import com.example.stockup.utils.StockListState
import com.example.stockup.utils.StockPageState
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: StocksViewModel) {


    val stockListState by viewModel.stockListState.collectAsStateWithLifecycle()
    val searchedStockState by viewModel.SearchedStockListState.collectAsStateWithLifecycle()
    var stockList: List<Any> = emptyList()

    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            HomeScreenTopAppBar(viewModel = viewModel)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Log.d("searching data", viewModel.isSearching.value.toString())

            when (stockListState) {
                is StockListState.Error -> Text(
                    "Error: ${(stockListState as StockListState.Error).errorMessage}",
                    Modifier.padding(12.dp)
                )

                is StockListState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }

                }

                is StockListState.Success<*> -> stockList =
                    (stockListState as StockListState.Success<*>).data as List<StockListData>
            }










            if (viewModel.isSearching.value) {
                when (searchedStockState) {
                    is StockListState.Error -> {
                        Text("Error: ${(searchedStockState as StockListState.Error).errorMessage}")
                    }

                    is StockListState.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is StockListState.Success<*> -> {
                        stockList =
                            (searchedStockState as StockListState.Success<*>).data as List<StockSearchData>
                        LazyColumn {
                            items(stockList) { stock ->
                                // Display search results
                                StockItem(
                                    stockSearchData = stock as StockSearchData,
                                    viewModel = viewModel
                                ) // Example: Display the symbol
                            }
                        }
                    }
                }
            } else {
                // Display the regular stock list
                LazyColumn {
                    items(stockList) { stock ->
                        StockItem(stockListData = stock as StockListData, viewModel = viewModel)
                    }
                }
            }
        }
    }
}

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: StocksViewModel) {
    var searchQuery by viewModel.searchQuery

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val stockListState by viewModel.stockListState.collectAsStateWithLifecycle()
        var stockList: List<Any> = emptyList()
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

        Text(
            text = "STOCKUP",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFFF8F9FA),
            modifier = Modifier
                .background(color = Color(0xFF007BFF))
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        if (viewModel.isSearching.value) {
            val searchedStockState by viewModel.SearchedStockListState.collectAsStateWithLifecycle()
            when (searchedStockState) {
                is StockListState.Error -> {
                    Text(
                        "Error: ${(stockListState as StockListState.Error).errorMessage}",
                        Modifier.padding(12.dp)
                    )
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
                        (stockListState as StockListState.Success<*>).data as List<StockSearchData>
                }
            }

        }



        //TODO:Remaining to fix search feature
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        var showCloseIcon by remember { mutableStateOf(false) } // Stateto control icon visibility

        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.searchQuery.value = searchQuery
                viewModel.isSearching.value = true
                viewModel.onEvent(StockPageState.OnSearchQuery(searchQuery))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { showCloseIcon = it.isFocused }, // Update state on focus change
            trailingIcon = {
                IconButton(onClick = {
                    if (showCloseIcon) {
                        viewModel.searchQuery.value = ""
                        searchQuery = ""
                        viewModel.isSearching.value = false
                        focusManager.clearFocus()
                    } else {
                        focusRequester.requestFocus()
                    }
                }) {
                    if (showCloseIcon) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    } else {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                }
            }
        )




        val searchedStockState by viewModel.SearchedStockListState.collectAsStateWithLifecycle()

        if (viewModel.isSearching.value) {
            when (searchedStockState) {
                is StockListState.Error -> {
                    Text("Error: ${(searchedStockState as StockListState.Error).errorMessage}")
                }

                is StockListState.Loading -> {
                    CircularProgressIndicator()
                }

                is StockListState.Success<*> -> {
                    val searchResults =
                        (searchedStockState as StockListState.Success<*>).data as List<StockSearchData>
                    LazyColumn {
                        items(searchResults) { stock ->
                            // Display search results
                            StockItem(stockSearchData = stock, viewModel = viewModel) // Example: Display the symbol
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


//    LazyColumn(
//
//    ) {
//        items(stockList) {
//            StockItem(stockListData = it as StockListData, viewModel = viewModel)
//        }
//    }
}

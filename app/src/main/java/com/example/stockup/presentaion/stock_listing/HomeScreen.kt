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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.stockup.domain.models.stockListings.StockListData
import com.example.stockup.presentaion.viewmodels.StocksViewModel
import com.example.stockup.utils.StockListState

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

        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp , bottom = 8.dp)
        )


        LazyColumn(

        ) {
            items(stockList) {
                StockItem(stockListData = it as StockListData, viewModel = viewModel)
            }
        }
    }
}
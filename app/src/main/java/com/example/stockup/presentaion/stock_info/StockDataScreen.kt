package com.example.stockup.presentaion.stock_info

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.stockup.domain.models.stockQuote.StockQuoteModel
import com.example.stockup.presentaion.viewmodels.StockDataViewModel
import com.example.stockup.presentaion.viewmodels.StocksViewModel
import com.example.stockup.utils.StockQuoteState
import kotlinx.coroutines.Dispatchers

@Composable
fun StockDataScreen(modifier: Modifier = Modifier  , viewModel: StockDataViewModel , navController: NavController) {

    val dataState = viewModel.stockQuoteState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.GetStockData()
    }


    Scaffold(
        topBar = {
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
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, bottom = 64.dp)
                    .imePadding(),
                horizontalArrangement = Arrangement.Start,
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                        viewModel.resetState()
                    },
                    modifier = Modifier
                        .clip(CircleShape) // Make the button circular
                        .background(Color(0xFF007BFF))
                        .size(55.dp)) {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .clip(CircleShape) // Make the button circular
                            .background(Color(0xFF007BFF))
                            .size(40.dp)
                    )
                }

            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            when (dataState.value) {
                is StockQuoteState.Error -> {
                    // Display error composable
                    Text("Error: ${(dataState.value as StockQuoteState.Error).errorMessage}")
                }
                is StockQuoteState.Loading -> {
                    // Display loading composable
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is StockQuoteState.Success -> {
                    // Display success composable
                    val data = (dataState.value as StockQuoteState.Success).data
                    if (data != null) {
                        InfoScreen(data = data)
                    } else {
                        // Handle case where data is null
                        Text("Data not available")
                    }
                }

                is StockQuoteState.DataNotAvailable -> {
                    Text("Data not available")
                }
            }
        }
    }
}


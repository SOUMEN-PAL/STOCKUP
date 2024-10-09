package com.example.stockup.presentaion.stock_info

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.stockup.R
import com.example.stockup.domain.models.stockQuote.FiftyTwoWeek
import com.example.stockup.domain.models.stockQuote.StockQuoteModel

@Composable
fun InfoScreen(modifier: Modifier = Modifier, data: StockQuoteModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(400.dp),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp,
                focusedElevation = 4.dp,
                hoveredElevation = 4.dp,
                draggedElevation = 8.dp
            ),

            border = BorderStroke(2.dp, Color(0xFF007BFF))

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA))
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    val companyName = data.name.split(" ")
                    val cleanedCompanyName = data.name?.split(" ")?.map { it.replace("[^A-Za-z0-9 ]".toRegex(), "") } ?: listOf("")
                    AsyncImage(
                        model = "https://logo.clearbit.com/${cleanedCompanyName.get(0)}.com",
                        contentDescription = "Translated description of what the image contains",
                        modifier = Modifier.weight(1f),
                        placeholder = painterResource(id = R.drawable.warehouse_2897818),
                        error = painterResource(id = R.drawable.warehouse_2897818)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.5f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Company Name",
                        fontSize = 24.sp,
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = data.name, fontSize = 20.sp , overflow = TextOverflow.Ellipsis)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Stock Price",
                        fontSize = 24.sp,
                        textDecoration = TextDecoration.Underline
                    )
                    Text(text = "${data.currency} ${data.close}", fontSize = 16.sp)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Percent Change",
                        fontSize = 24.sp,
                        textDecoration = TextDecoration.Underline
                    )
                    Text(
                        text = data.percent_change + " %",
                        color = if (data.percent_change.toFloat() > 0.0) {
                            Color(0xFF28A745)
                        } else {
                            Color(0xFFDC3545)
                        },
                        fontSize = 16.sp
                    )
                }
            }

        }
    }
}

val dummyStockQuoteModel = StockQuoteModel(
    average_volume = "1000000",
    change = "1.50",
    close = "150.00",
    currency = "USD",
    datetime = "2023-10-27T10:00:00Z",
    exchange = "NYSE",
    fifty_two_week = FiftyTwoWeek(
        high = "200.00",
        high_change = "50.00",
        high_change_percent = "25.00",
        low = "100.00",
        low_change = "-50.00",
        low_change_percent = "-50.00",
        range = "100.00 - 200.00"
    ),
    high = "152.00",
    is_market_open = true,
    low = "148.00",
    mic_code = "XNYS",
    name = "Example Company",
    `open` = "149.50",
    percent_change = "1.00",
    previous_close = "148.50",
    symbol = "EXCO",
    timestamp = 1698426000,
    volume = "500000"
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen(data = dummyStockQuoteModel)
}
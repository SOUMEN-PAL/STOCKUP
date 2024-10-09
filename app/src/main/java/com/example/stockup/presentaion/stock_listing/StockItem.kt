package com.example.stockup.presentaion.stock_listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.stockup.R
import com.example.stockup.domain.models.stockListings.StockListData
import com.example.stockup.presentaion.viewmodels.StocksViewModel

import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.example.stockup.domain.models.stockSearching.StockSearchData


@Composable
fun StockItem(modifier: Modifier = Modifier, stockListData: StockListData , viewModel: StocksViewModel , onCLick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().clickable {
        onCLick()
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val companyName = stockListData.name.split(" ")
            val cleanedCompanyName = stockListData.name?.split(" ")?.map { it.replace("[^A-Za-z0-9 ]".toRegex(), "") } ?: listOf("")
            AsyncImage(
                model = "https://logo.clearbit.com/${cleanedCompanyName.get(0)}.com",
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier.weight(1f),
                placeholder = painterResource(id = R.drawable.warehouse_2897818),
                error = painterResource(id = R.drawable.warehouse_2897818)
            )


            

            Column(modifier = Modifier.weight(3f)) {
                Row {
                    Text(
                        text = stockListData.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Text(
                    text = " (${stockListData.symbol})",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

            Text(
                text = stockListData.exchange,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider(
            color = Color(0xFF007BFF),
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
    }

}

@Composable
fun StockItem(modifier: Modifier = Modifier, stockSearchData: StockSearchData , viewModel: StocksViewModel , onCLick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().clickable {
        onCLick()
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            val companyName = stockSearchData.instrument_name.split(" ")
            val cleanedCompanyName = stockSearchData.instrument_type?.split(" ")?.map { it.replace("[^A-Za-z0-9 ]".toRegex(), "") } ?: listOf("")
            AsyncImage(
                model = "https://logo.clearbit.com/${cleanedCompanyName.get(0)}.com",
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier.weight(1f),
                placeholder = painterResource(id = R.drawable.warehouse_2897818),
                error = painterResource(id = R.drawable.warehouse_2897818)
            )




            Column(modifier = Modifier.weight(3f)) {
                Row {
                    Text(
                        text = stockSearchData.instrument_name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Text(
                    text = " (${stockSearchData.symbol})",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

            Text(
                text = stockSearchData.exchange,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider(
            color = Color(0xFF007BFF),
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
    }

}


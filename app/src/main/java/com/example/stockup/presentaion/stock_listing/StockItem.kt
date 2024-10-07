package com.example.stockup.presentaion.stock_listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.stockup.domain.models.stockListings.StockListData
import com.example.stockup.utils.StockListState

@Composable
fun StockItem(modifier: Modifier = Modifier , stockListData: StockListData) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
//        Text(text = stockListData.symbol)
//        Text(text = stockListData.name)

        val companyName = stockListData.name.split(" ")


        AsyncImage(
            model = "https://logo.clearbit.com/${companyName.get(0)}.com",
            contentDescription = "Translated description of what the image contains"
        )

    }
}
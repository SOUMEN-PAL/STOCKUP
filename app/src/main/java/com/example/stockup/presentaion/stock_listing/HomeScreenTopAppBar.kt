package com.example.stockup.presentaion.stock_listing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.stockup.presentaion.viewmodels.StocksViewModel
import com.example.stockup.utils.StockPageState

@Composable
fun HomeScreenTopAppBar(modifier: Modifier = Modifier, viewModel: StocksViewModel) {
    var searchQuery by viewModel.searchQuery
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isFocused by rememberSaveable { mutableStateOf(viewModel.isSearching.value) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ){

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
                viewModel.searchQuery.value = searchQuery
                viewModel.onEvent(StockPageState.OnSearchQuery(searchQuery))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    if (isFocused) {
                        viewModel.isSearching.value = true
                    }
                }, // Update state on focus change
            trailingIcon = {
                IconButton(onClick = {
                    if (isFocused) {
                        viewModel.searchQuery.value = ""
                        searchQuery = ""
                        viewModel.resetSearchStockList()
                        viewModel.isSearching.value = false
                        focusManager.clearFocus()
                    } else {
                        viewModel.isSearching.value = true
                        focusRequester.requestFocus()
                    }
                }) {
                    if (isFocused) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    } else {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                }
            }
        )
    }
    LaunchedEffect(viewModel.isSearching.value) {
        if (viewModel.isSearching.value) {
            focusRequester.requestFocus()
        }
    }
}
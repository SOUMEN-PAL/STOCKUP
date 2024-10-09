package com.example.stockup.utils

import com.example.stockup.domain.models.stockQuote.StockQuoteModel

sealed class StockQuoteState {
    class Loading():StockQuoteState()
    class Error(val errorMessage : String) : StockQuoteState()
    class Success(var data : StockQuoteModel?) : StockQuoteState()
    class DataNotAvailable : StockQuoteState()
}
package com.example.stockup.domain.models.stockQuote

data class ErrorModel(
    val code: Int,
    val message: String,
    val meta: Meta,
    val status: String
)
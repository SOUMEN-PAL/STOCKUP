package com.example.stockup.presentaion.navigation

//Managing Screen Routes externally for proper navigation
sealed class Screens(val route : String) {
    data object homeScreen : Screens("homeScreen")
    data object stockDataScreen : Screens("stockDataScreen")
}
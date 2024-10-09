package com.example.stockup.presentaion.errorHandlingUI

import android.content.Context
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.stockup.presentaion.viewmodels.StocksViewModel



@Composable
fun NetworkDialogBox(showDialog: Boolean, onClick: ()-> Unit, context: Context, viewModel: StocksViewModel) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Do nothing, prevent dismissing by clicking outside */ },
            title = { Text("Network Error") },
            text = { Text("Please check your network connection.") },
            confirmButton = {
                Button(onClick = {
                    onClick()
                }) {
                    Text("OK")
                }
            }
        )
    }
}
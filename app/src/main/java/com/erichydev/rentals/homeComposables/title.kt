package com.erichydev.rentals.homeComposables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title() {
    Text(
        text = "Rentals",
        modifier = Modifier
            .padding(bottom = 10.dp),
        fontSize = 20.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
    )
}
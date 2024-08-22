package com.erichydev.rentals.homeComposables.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erichydev.rentals.homeComposables.HomeViewModel

@Composable
fun PriceFilter(
    homeViewModel: HomeViewModel
) {
    val fetchedPlots by homeViewModel.fetchedPlots.observeAsState(emptyList())
    val priceFilter by homeViewModel.priceFilter.observeAsState(true)

    Row(
        modifier = Modifier
            .clickable {
                homeViewModel.togglePriceFilter()
                homeViewModel.setFetchedPlots(
                    if(priceFilter) {
                        fetchedPlots.sortedBy { it.plotPrice }
                    } else {
                        fetchedPlots.sortedByDescending { it.plotPrice }
                    }
                )
            }
            .background(Color(0xFF002AFF), shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = "Price",
            modifier = Modifier
                .padding(vertical = 5.dp)
                .padding(start = 5.dp),
            color = Color(0xFFedf2f4),
            fontSize = 13.sp
        )
        Icon(
            imageVector = if(priceFilter) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "price",
            modifier = Modifier
                .height(12.dp)
                .padding(end = 5.dp),
            tint = Color(0xFFedf2f4)
        )
    }
}
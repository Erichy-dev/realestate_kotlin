package com.erichydev.rentals.homeComposables.filters

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
fun UploadedFilter(
    homeViewModel: HomeViewModel
) {
    val fetchedPlots by homeViewModel.fetchedPlots.observeAsState(emptyList())
    val uploadedFilter by homeViewModel.uploadedFilter.observeAsState(true)

    Row(
        modifier = Modifier
            .clickable {
                homeViewModel.toggleUploadedFilter()
                homeViewModel.setFetchedPlots(fetchedPlots.sortedWith { plot1, plot2 ->
                    if (uploadedFilter) {
                        plot1.plotUploadDate.compareTo(plot2.plotUploadDate)
                    } else {
                        plot2.plotUploadDate.compareTo(plot1.plotUploadDate)
                    }
                })
            }
            .border(
                width = 1.dp,
                color = Color(0xFFedf2f4),
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = "Uploaded",
            modifier = Modifier
                .padding(vertical = 5.dp)
                .padding(start = 5.dp),
            color = Color(0xFFedf2f4),
            fontSize = 13.sp
        )
        Icon(
            imageVector = if (uploadedFilter) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "uploaded",
            modifier = Modifier
                .height(12.dp)
                .padding(end = 5.dp),
            tint = Color(0xFFedf2f4)
        )
    }
}
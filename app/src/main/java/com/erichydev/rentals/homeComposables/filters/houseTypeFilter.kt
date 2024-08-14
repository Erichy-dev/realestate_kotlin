package com.erichydev.rentals.homeComposables.filters

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erichydev.rentals.homeComposables.HomeViewModel

@Composable
fun HouseTypeFilter(
    viewModel: HomeViewModel
) {
    val expandedRooms by viewModel.expandedRooms.observeAsState(false)
    val originalPlots by viewModel.originalPlots.observeAsState(emptyList())
    val fetchedPlots by viewModel.fetchedPlots.observeAsState(emptyList())

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFedf2f4),
                    shape = RoundedCornerShape(6.dp)
                ),
        ) {
            Row(
                modifier = Modifier
                    .clickable { viewModel.setExpandedRooms(true) }
                    .border(
                        width = 1.dp,
                        color = Color(0xFFedf2f4),
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    text = "${viewModel.selectedRoomOption.value}",
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .padding(start = 5.dp),
                    color = Color(0xFFedf2f4),
                    fontSize = 13.sp
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "house-type",
                    modifier = Modifier
                        .height(12.dp)
                        .padding(end = 5.dp),
                    tint = Color(0xFFedf2f4)
                )
            }
        }
        DropdownMenu(
            expanded = expandedRooms,
            onDismissRequest = { viewModel.setExpandedRooms(false) },
        ) {
            DropdownMenuItem(
                onClick = {
                    viewModel.setExpandedRooms(false)
                    viewModel.setSelectedRoomOption("Single")
                    viewModel.setFetchedPlots(originalPlots.filter { plot -> plot.plotSingle })
                },
                text = { Text("Single") }
            )
            DropdownMenuItem(
                onClick = {
                    viewModel.setExpandedRooms(false)
                    viewModel.setSelectedRoomOption("Bedsitter")
                    viewModel.setFetchedPlots(originalPlots.filter { plot -> plot.plotBedsitter })
                },
                text = { Text("Bedsitter") }
            )
            DropdownMenuItem(
                onClick = {
                    viewModel.setExpandedRooms(false)
                    viewModel.setSelectedRoomOption("1-Bedroom")
                    viewModel.setFetchedPlots(originalPlots.filter { plot -> plot.plot1B })
                },
                text = { Text("1-Bedroom") }
            )
            DropdownMenuItem(
                onClick = {
                    viewModel.setExpandedRooms(false)
                    viewModel.setSelectedRoomOption("2-Bedroom")
                    viewModel.setFetchedPlots(originalPlots.filter { plot -> plot.plot2B })
                },
                text = { Text("2-Bedroom") }
            )
            DropdownMenuItem(
                onClick = {
                    viewModel.setExpandedRooms(false)
                    viewModel.setSelectedRoomOption("Bedsitter")
                    viewModel.setFetchedPlots(originalPlots)
                },
                text = { Text("Reset") }
            )
        }
    }
}
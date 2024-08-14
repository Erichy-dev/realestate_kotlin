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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HouseTypeFilter() {
    var expandedRooms by remember {
        mutableStateOf(false)
    }

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
                    .clickable { expandedRooms = true }
                    .border(
                        width = 1.dp,
                        color = Color(0xFFedf2f4),
                        shape = RoundedCornerShape(8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    text = "Bedsitter",
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
            onDismissRequest = {  },
        ) {
            DropdownMenuItem(
                onClick = {
                    expandedRooms = false
                },
                text = { Text("Single") }
            )
            DropdownMenuItem(
                onClick = {
                    expandedRooms = false
                },
                text = { Text("Bedsitter") }
            )
            DropdownMenuItem(
                onClick = {
                    expandedRooms = false
                },
                text = { Text("1-Bedroom") }
            )
            DropdownMenuItem(
                onClick = {
                    expandedRooms = false
                },
                text = { Text("2-Bedroom") }
            )
        }
    }
}
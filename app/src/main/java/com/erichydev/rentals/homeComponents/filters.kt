package com.erichydev.rentals.homeComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Filters(
    homeViewModel: HomeViewModel
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
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

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFedf2f4),
                    shape = RoundedCornerShape(8.dp)
                ),
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
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "price",
                modifier = Modifier
                    .height(12.dp)
                    .padding(end = 5.dp),
                tint = Color(0xFFedf2f4)
            )
        }

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFedf2f4),
                    shape = RoundedCornerShape(8.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = "Rating",
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .padding(start = 5.dp),
                color = Color(0xFFedf2f4),
                fontSize = 13.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "rating",
                modifier = Modifier
                    .height(12.dp)
                    .padding(end = 5.dp),
                tint = Color(0xFFedf2f4)
            )
        }
        Row(
            modifier = Modifier
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
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "uploaded",
                modifier = Modifier
                    .height(12.dp)
                    .padding(end = 5.dp),
                tint = Color(0xFFedf2f4)
            )
        }
    }
}
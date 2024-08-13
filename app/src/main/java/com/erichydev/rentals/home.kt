package com.erichydev.rentals

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erichydev.rentals.homeComponents.Filters
import com.erichydev.rentals.homeComponents.PrivacyPolicy
import com.erichydev.rentals.homeComponents.Title
import com.erichydev.rentals.ui.theme.LinkText

@Composable
fun Home() {
    Scaffold {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                item {
                    Title()
                }

                item {
                    Filters()
                }
            }

            PrivacyPolicy()
        }
    }
}
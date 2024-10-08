package com.erichydev.rentals.homeComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.erichydev.rentals.homeComposables.filters.HouseTypeFilter
import com.erichydev.rentals.homeComposables.filters.PriceFilter
import com.erichydev.rentals.homeComposables.filters.RatingFilter
import com.erichydev.rentals.homeComposables.filters.UploadedFilter

@Composable
fun Filters(
    homeViewModel: HomeViewModel
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        HouseTypeFilter(homeViewModel)

        PriceFilter(homeViewModel)

        RatingFilter(homeViewModel)

        UploadedFilter(homeViewModel)
    }
}
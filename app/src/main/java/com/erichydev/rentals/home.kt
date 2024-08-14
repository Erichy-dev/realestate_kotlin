package com.erichydev.rentals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.erichydev.rentals.homeComposables.Filters
import com.erichydev.rentals.homeComposables.HomeViewModel
import com.erichydev.rentals.homeComposables.PlotView
import com.erichydev.rentals.homeComposables.PrivacyPolicy
import com.erichydev.rentals.homeComposables.Title
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(
    onPlotClick: (plotId: String) -> Unit
) {
    val context = LocalContext.current

    val homeViewModel: HomeViewModel = viewModel()
    val fetchedPlots by homeViewModel.fetchedPlots.observeAsState(emptyList())

    homeViewModel.fetchPlots(context)

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Scaffold {
        Box(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Column {
                        Title()

                        Filters(homeViewModel)
                    }
                }

                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .height(screenHeight)
                    ) {
                        items(fetchedPlots, key = { plot -> plot.plotNumber }) {plot ->
                            PlotView(plot, onPlotClick)
                        }
                    }
                }
            }

            PrivacyPolicy()
        }
    }
}
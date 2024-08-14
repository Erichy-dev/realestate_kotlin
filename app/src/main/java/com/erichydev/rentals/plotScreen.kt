package com.erichydev.rentals

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.erichydev.rentals.plotComposables.Loader
import com.erichydev.rentals.plotComposables.PlotViewModel

@Composable
fun PlotScreen(plotId: String) {
    val plotViewModel: PlotViewModel = viewModel()
    val context = LocalContext.current

    // plot pics
    val plotPics by plotViewModel.plotPics.observeAsState(emptyList())
    plotViewModel.fetchPlotPics(context, plotId)

    val imagesLoaded by plotViewModel.imagesLoaded.observeAsState(0)

    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            when {
                plotPics.isEmpty() || imagesLoaded < plotPics.size -> {
                    Loader()
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item{
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(plotPics) { pic ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(pic.plotPic)
                                    .crossfade(true)
                                    .listener(
                                        onSuccess = { _, _ ->
                                            plotViewModel.setImagesLoaded(imagesLoaded + 1)
                                        }
                                    )
                                    .build(),
                                contentDescription = "nyumba kumi",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(400.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
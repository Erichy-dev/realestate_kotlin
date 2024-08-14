package com.erichydev.rentals

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.erichydev.rentals.data.Plot
import com.erichydev.rentals.plotComposables.Loader
import com.erichydev.rentals.plotComposables.PlotContacts
import com.erichydev.rentals.plotComposables.PlotPicsView
import com.erichydev.rentals.plotComposables.PlotRating
import com.erichydev.rentals.plotComposables.PlotViewModel
import kotlinx.coroutines.delay

@Composable
fun PlotScreen(plotId: String) {
    val plotViewModel: PlotViewModel = viewModel()
    val context = LocalContext.current

    // plot pics
    val plotPics by plotViewModel.plotPics.observeAsState(emptyList())
    plotViewModel.fetchPlotPics(context, plotId)
    val imagesLoaded by plotViewModel.imagesLoaded.observeAsState(0)

    // plot details
    val plot by plotViewModel.plotDetails.observeAsState(Plot(
        "",
        "",
        "",
        0,
        plotSingle = false,
        plotBedsitter = false,
        plot1B = false,
        plot2B = false,
        plot3B = false,
        0,
        ""
    ))
    plotViewModel.fetchPlotDetails(context, plotId)

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
                    PlotPicsView(plotPics, imagesLoaded, plotViewModel)
                }

                // plot details
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(30.dp),
                        modifier = Modifier
                            .padding(it)
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                    ) {
                        PlotRating(plot)

                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("file:///android_asset/magic.svg")
                                    .decoderFactory(SvgDecoder.Factory())
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "home",
                                modifier = Modifier
                                    .height(20.dp)
                            )

                            Text(text = "Plot Contacts")

                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("file:///android_asset/magic.svg")
                                    .decoderFactory(SvgDecoder.Factory())
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "home",
                                modifier = Modifier
                                    .height(20.dp)
                            )
                        }

                        PlotContacts(plotViewModel, plotId)
                    }
                }
            }
        }
    }
}
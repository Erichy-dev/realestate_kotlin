package com.erichydev.rentals.homeComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.erichydev.rentals.data.Plot

@Composable
fun PlotView(
    plot: Plot
) {
    val imageHeight = remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(plot.plotBgPic)
                    .crossfade(true)
                    .build(),
                contentDescription = "home",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
                    .clip(RoundedCornerShape(8.dp))  // Apply rounded corners
                    .onGloballyPositioned { layoutCoordinates ->
                        imageHeight.value = with(density) { (layoutCoordinates.size.height).toDp() }
                    }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .height(imageHeight.value)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(horizontal = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                    val roomTypes = remember(plot.plotSingle, plot.plotBedsitter, plot.plot1B, plot.plot2B, plot.plot3B) {
                        buildList {
                            if (plot.plotSingle) add("Single Room")
                            if (plot.plotBedsitter) add("Bedsitter")
                            if (plot.plot1B) add("1 Bedroom")
                            if (plot.plot2B) add("2 Bedroom")
                        }
                    }
                    val value = if (roomTypes.isNotEmpty()) {
                        roomTypes.joinToString(", ")
                    } else {
                        "No rooms specified"
                    }
                    Text(
                        text = value,
                        modifier = Modifier
                    )
                    Text(
                        text = "Ksh.${"%,d".format(plot.plotPrice)}",
                        modifier = Modifier,
                    )
                if (plot.plotRating > 0) {
                    LazyRow {
                        items(plot.plotRating) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("file:///android_asset/star.svg")
                                    .decoderFactory(SvgDecoder.Factory())
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "home",
                                modifier = Modifier
                                    .size(15.dp)  // Optimized image size
                            )
                        }
                    }
                }
            }
        }
    }
}
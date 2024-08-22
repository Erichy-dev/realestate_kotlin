package com.erichydev.rentals.plotComposables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.erichydev.rentals.data.Plot

@Composable
fun PlotRating(
    plot: Plot
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        LazyRow {
            items(5) { index ->
                val starImage = if (index < plot.plotRating) {
                    "file:///android_asset/star.svg"
                } else {
                    "file:///android_asset/un-star.svg"
                }

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(starImage)
                        .decoderFactory(SvgDecoder.Factory())
                        .crossfade(true)
                        .build(),
                    contentDescription = if (index < plot.plotRating) "rated star" else "unrated star",
                    modifier = Modifier
                        .height(15.dp)
                        .width(15.dp)
                )
            }
        }
    }
}
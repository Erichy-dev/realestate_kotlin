package com.erichydev.rentals.plotComposables

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.delay

@Composable
fun PlotContacts(
    plotViewModel: PlotViewModel,
    plotId: String
) {
    val context = LocalContext.current

    val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val copyState = remember { mutableStateMapOf<String, Boolean>() }
    fun copyCaretakerNo(phone: String) {
        copyState[phone] = true
        val clip = ClipData.newPlainText("copied_phone_number", phone)
        clipboardManager.setPrimaryClip(clip)
    }

    val plotCaretakers by plotViewModel.plotCaretakers.observeAsState(emptyList())
    plotViewModel.fetchPlotCaretakers(context, plotId)

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .background(Color(0xFF000000), shape = RoundedCornerShape(8.dp))
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = "Caretakers", modifier = Modifier.padding(end = 20.dp))

            LazyColumn(
                modifier = Modifier
                    .height((plotCaretakers.size * 20).dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(plotCaretakers) { caretaker ->
                    val phone = caretaker.plotOccupantPhone
                    val isCopied = copyState[phone] ?: false

                    LaunchedEffect(isCopied) {
                        if (isCopied) {
                            delay(2000)
                            copyState[phone] = false
                        }
                    }

                    LazyRow(
                        modifier = Modifier.clickable { copyCaretakerNo(phone) },
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item {
                            Text(
                                text = phone,
                                textDecoration = TextDecoration.Underline,
                                color = if (isCopied) Color(0xFF63E6BE) else Color(0xFFedf2f4)
                            )
                        }
                        item {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("file:///android_asset/${if (isCopied) "copied.svg" else "copy.svg"}")
                                    .decoderFactory(SvgDecoder.Factory())
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "home",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(15.dp)
                            )
                        }
                        if(isCopied) {
                            item {
                                CaretakerPhoneNumber(phone)
                            }
                        }
                    }
                }
            }
        }
    }
}
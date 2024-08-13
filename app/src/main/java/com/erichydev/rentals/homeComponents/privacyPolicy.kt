package com.erichydev.rentals.homeComponents

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.erichydev.rentals.ui.theme.LinkText

@Composable
fun PrivacyPolicy() {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()){
        LinkText {
            Text(
                text = "Privacy Policy",
                modifier = Modifier
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.nyumbakumi.net/privacyPolicy")
                        )
                        context.startActivity(intent)
                    }
                    .align(Alignment.BottomCenter)
                    .padding(10.dp),
            )
        }
    }
}
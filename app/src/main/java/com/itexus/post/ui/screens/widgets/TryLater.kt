package com.itexus.post.ui.screens.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.itexus.post.R

@Composable
fun TryLater(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable(enabled = false) {}
            .background(Color(0x60000000))
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        Button(onClick = onClick) {
            Text(
                text = stringResource(id = R.string.try_again),
                style = MaterialTheme.typography.h4
            )
        }
    }
}
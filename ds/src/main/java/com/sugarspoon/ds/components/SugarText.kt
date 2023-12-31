package com.sugarspoon.ds.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SugarText(
    modifier: Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Left,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Text(text = text, modifier = modifier, style = style, textAlign = textAlign)
}
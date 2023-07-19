package com.sugarspoon.ds.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun SugarButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        shape = RectangleShape,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.background
        )
    ) {
        SugarText(
            text = text,
            modifier = Modifier,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
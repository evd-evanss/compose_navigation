package com.sugarspoon.ds.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sugarspoon.ds.R

@Composable
fun SugarTopAppBar(
    title: String,
    onBackPressed: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        onBackPressed?.let {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .clickable(
                        onClick = onBackPressed,
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    )
            )
        }

        SugarText(
            text = title,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.background)
        )
    }
}
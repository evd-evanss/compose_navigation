package com.sugarspoon.ds.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sugarspoon.ds.R

@Composable
fun SugarCardCheckBox(
    modifier: Modifier,
    headline: String,
    price: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val interactionSource = MutableInteractionSource()
    Card(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = rememberRipple(),
            onClick = { onCheckedChange(isChecked.not())}
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                SugarText(
                    text = headline,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                SugarText(
                    text = price,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Image(
                painter = painterResource(id = R.drawable.lemonade),
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.weight(3f))
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                interactionSource = interactionSource
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun SugarCard(
    modifier: Modifier,
    headline: String,
    price: String,
) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SugarText(
                text = headline,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.weight(3f))
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.lemonade),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).padding(top = 16.dp)
                )
                SugarText(
                    text = price,
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
package com.sugarspoon.ds.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sugarspoon.ds.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SugarLoading(
    display: Boolean,
    onHide: (Boolean) -> Unit,
    actionOnFinish: () -> Unit
) {
    if(display) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lemon_juice))
            val progress by animateLottieCompositionAsState(composition, isPlaying = display)

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
    LaunchedEffect(display) {
        launch {
            delay(3000)
            if(display) {
                onHide(false)
                actionOnFinish()
            }
        }
    }
}
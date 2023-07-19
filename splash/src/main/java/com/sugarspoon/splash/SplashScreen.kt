package com.sugarspoon.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sugarspoon.navigation.NavigationRoute
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashRouteImpl @Inject constructor(): NavigationRoute {

    @Composable
    override fun Screen(navHostController: NavHostController, entry: NavBackStackEntry) {
        SplashScreen(navController = navHostController, routes = this.routes)
    }
}

@Composable
private fun SplashScreen(
    navController: NavController,
    routes: NavigationRoute.Routes
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lemonade))
    val progress by animateLottieCompositionAsState(composition)

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    LaunchedEffect(key1 = true) {
        launch {
            delay(3000)
            navController.currentBackStackEntry?.savedStateHandle?.set("clearAll", true)
            navController.navigate(routes.ORDER) {
                popUpTo(routes.SPLASH) {
                    inclusive = true
                }
            }
        }
    }
}
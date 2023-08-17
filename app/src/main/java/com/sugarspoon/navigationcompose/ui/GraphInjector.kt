package com.sugarspoon.navigationcompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sugarspoon.navigation.NavigationRoute
import com.sugarspoon.order.OrderRouteImpl
import com.sugarspoon.shoppingcart.ShoppingCartRouteImpl
import com.sugarspoon.splash.SplashRouteImpl
import javax.inject.Inject

class GraphInjector @Inject constructor(
    private val orderRouteImpl: OrderRouteImpl,
    private val shoppingCartRouteImpl: ShoppingCartRouteImpl,
    private val splashRouteImpl: SplashRouteImpl
) {

    @Composable
    fun Inject(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.Routes.SPLASH
        ) {
            splashRouteImpl.run { screen(navController) }
            orderRouteImpl.run { screen(navController) }
            shoppingCartRouteImpl.run { screen(navController) }
        }
    }
}
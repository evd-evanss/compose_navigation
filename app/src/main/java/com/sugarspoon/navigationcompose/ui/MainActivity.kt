package com.sugarspoon.navigationcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sugarspoon.shoppingcart.ShoppingCartRouteImpl
import com.sugarspoon.order.OrderRouteImpl
import com.sugarspoon.navigation.NavigationRoute
import com.sugarspoon.splash.SplashRouteImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var orderRouteImpl: OrderRouteImpl
    @Inject
    lateinit var shoppingCartRouteImpl: ShoppingCartRouteImpl
    @Inject
    lateinit var splashRouteImpl: SplashRouteImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            com.sugarspoon.theme.NavigationComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(navController = navController)
                }
            }
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.Routes.SPLASH
        ) {

            composable(NavigationRoute.Routes.SPLASH) { entry ->
                splashRouteImpl.Screen(navHostController = navController, entry = entry)
            }

            composable(NavigationRoute.Routes.ORDER) { entry ->
                orderRouteImpl.Screen(navHostController = navController, entry = entry)
            }

            composable(NavigationRoute.Routes.SHOPPING_CART) { entry ->
                shoppingCartRouteImpl.Screen(navHostController = navController, entry = entry)
            }
        }
    }
}
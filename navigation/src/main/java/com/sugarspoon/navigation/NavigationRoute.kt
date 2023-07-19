package com.sugarspoon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

interface NavigationRoute {

    object Routes {
        const val SPLASH = "splash"
        const val ORDER = "order"
        const val SHOPPING_CART = "shopping_cart"
    }

    val routes: Routes
        get() = Routes

    @Composable
    fun Screen(
        navHostController: NavHostController,
        entry: NavBackStackEntry
    )

}
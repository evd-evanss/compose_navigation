package com.sugarspoon.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationRoute {

    object Routes {
        const val SPLASH = "splash"
        const val ORDER = "order"
        const val SHOPPING_CART = "shopping_cart"
        const val SUCCESS = "success"
    }

    val routes: Routes
        get() = Routes

    fun NavGraphBuilder.screen(navHostController: NavHostController)

}
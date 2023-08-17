package com.sugarspoon.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sugarspoon.ds.components.SugarButton
import com.sugarspoon.ds.components.SugarCardCheckBox
import com.sugarspoon.ds.components.SugarText
import com.sugarspoon.ds.components.SugarTopAppBar
import com.sugarspoon.navigation.NavigationRoute
import java.lang.String.format
import java.util.Locale
import javax.inject.Inject

class OrderRouteImpl @Inject constructor(
    private val viewModel: OrderViewModel,
) : NavigationRoute {

    override fun NavGraphBuilder.screen(navHostController: NavHostController) {
        composable(routes.ORDER) {
            OrderScreen(
                viewModel = viewModel,
                navHostController = navHostController,
                routes = routes
            )
        }
    }
}

@Composable
private fun OrderScreen(
    viewModel: OrderViewModel,
    navHostController: NavHostController,
    routes: NavigationRoute.Routes
) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        SugarTopAppBar(title = "Lemonades")
        Spacer(modifier = Modifier.weight(1f))
        SugarCardCheckBox(
            headline = "Lemonade 200ml",
            price = "R$ 2.00",
            modifier = Modifier.padding(16.dp),
            isChecked = state.smallLemonade,
            onCheckedChange = viewModel::onChoose200ml
        )
        SugarCardCheckBox(
            headline = "Lemonade 300ml",
            price = "R$ 3.00",
            modifier = Modifier.padding(16.dp),
            isChecked = state.mediumLemonade,
            onCheckedChange = viewModel::onChoose300ml
        )
        SugarCardCheckBox(
            headline = "Lemonade 500ml",
            price = "R$ 5.00",
            modifier = Modifier.padding(16.dp),
            isChecked = state.largeLemonade,
            onCheckedChange = viewModel::onChoose500ml
        )
        Spacer(modifier = Modifier.weight(2f))
        SugarText(
            modifier = Modifier.padding(16.dp),
            text = "Total R$ ${format(Locale.getDefault(), "%.2f", state.amount)}"
        )
        SugarButton(
            text = "GO TO SHOPPING CART",
            modifier = Modifier.fillMaxWidth()
        ) {
            viewModel.clearAll()
            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                key = "user",
                value = state.order.toList()
            )
            navHostController.navigate(routes.SHOPPING_CART)
        }
    }
}
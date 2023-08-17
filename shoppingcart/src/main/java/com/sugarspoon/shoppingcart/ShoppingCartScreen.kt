package com.sugarspoon.shoppingcart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sugarspoon.data.model.Order
import com.sugarspoon.ds.components.SugarButton
import com.sugarspoon.ds.components.SugarCard
import com.sugarspoon.ds.components.SugarLoading
import com.sugarspoon.ds.components.SugarText
import com.sugarspoon.ds.components.SugarTopAppBar
import com.sugarspoon.home.R
import com.sugarspoon.navigation.NavigationRoute
import javax.inject.Inject
import kotlinx.coroutines.launch

class ShoppingCartRouteImpl @Inject constructor(
    private val viewModel: ShoppingCartViewModel
) : NavigationRoute {

    override fun NavGraphBuilder.screen(navHostController: NavHostController) {
        composable(routes.SHOPPING_CART) {
            val state by viewModel.state.collectAsState()
            val order =
                navHostController.previousBackStackEntry?.savedStateHandle?.get<List<Order>>("user")
            LaunchedEffect(true) {
                launch {
                    order?.let { viewModel.getOrder(it) }
                }
            }
            if (state.displayFinishOrder) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.success_icon),
                        contentDescription = null
                    )
                    SugarText(
                        text = "Your order has been completed successfully, enjoy your lemonades!",
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SugarButton(
                        text = "CLOSE",
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.clearAll()
                            navHostController.clearBackStack(route = routes.ORDER)
                            navHostController.navigate(route = routes.SPLASH)
                        }
                    )
                }
            } else {
                ShoppingCartScreen(
                    state = state,
                    displayEmptyState = order.isNullOrEmpty(),
                    viewModel = viewModel
                ) {
                    navHostController.popBackStack()
                }
            }

            SugarLoading(
                display = state.isLoading,
                onHide = viewModel::displayLoading,
                actionOnFinish = viewModel::displayFinishOrder
            )
        }
    }
}

@Composable
private fun ShoppingCartScreen(
    state: HomeUiState,
    displayEmptyState: Boolean,
    viewModel: ShoppingCartViewModel,
    onBackPressed: () -> Unit
) {
    Column {
        SugarTopAppBar(title = "Shopping Cart", onBackPressed = onBackPressed)
        if (displayEmptyState) {
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.cart_empty), contentDescription = null)
            SugarText(
                text = "Your cart is empty. Start adding items to enjoy your lemonades!",
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            SugarButton(
                text = "RETURN",
                modifier = Modifier.fillMaxWidth(),
                onClick = onBackPressed
            )
        } else {
            SugarCard(
                modifier = Modifier.padding(16.dp),
                headline = state.headline,
                price = state.price
            )
            Spacer(modifier = Modifier.weight(1f))
            SugarButton(
                text = "BUY",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.displayLoading(true)
                }
            )
        }
    }
}
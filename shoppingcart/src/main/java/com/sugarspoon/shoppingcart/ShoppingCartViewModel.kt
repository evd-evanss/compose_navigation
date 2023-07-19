package com.sugarspoon.shoppingcart

import com.sugarspoon.commons.base.BaseViewModel
import com.sugarspoon.commons.base.UiEvents
import com.sugarspoon.commons.base.UiStates
import com.sugarspoon.data.model.Order
import java.lang.String.format
import java.util.Locale
import javax.inject.Inject

class ShoppingCartViewModel @Inject constructor(): BaseViewModel<HomeUiState, HomeUiEvents>(HomeUiState()) {

    override fun handler(oldState: HomeUiState, events: HomeUiEvents) {
        when(events) {
            is HomeUiEvents.GetOrder -> {
                var headline = ""
                events.orders.forEach {
                    headline += "1- " + it.type.description + "\n"
                }
                val amount = events.orders.map { it.price }.fold(0f) { acc, total ->
                    total.toFloat() + acc
                }
                createNewState(
                    newState = oldState.copy(
                        headline = headline,
                        price = "Total $${format(Locale.getDefault(), "%.2f", amount)}"
                    )
                )
            }

            is HomeUiEvents.ShowLoading -> {
                createNewState(newState = oldState.copy(isLoading = events.display))
            }

            is HomeUiEvents.DisplayFinishOrder -> {
                createNewState(
                    newState = oldState.copy(
                        isLoading = false,
                        displayFinishOrder = true
                    )
                )
            }

            is HomeUiEvents.ClearAll -> {
                createNewState(newState = HomeUiState())
            }
        }
    }

    fun clearAll() {
        emitEvent(HomeUiEvents.ClearAll)
    }

    fun displayFinishOrder() {
        emitEvent(HomeUiEvents.DisplayFinishOrder)
    }

    fun displayLoading(display: Boolean) {
        emitEvent(HomeUiEvents.ShowLoading(display))
    }

    fun getOrder(order: List<Order>) {
        emitEvent(HomeUiEvents.GetOrder(order))
    }
}

data class HomeUiState(
    val headline: String = "",
    val price: String = "0.00",
    val isLoading: Boolean = false,
    val displayFinishOrder: Boolean = false
): UiStates

sealed class HomeUiEvents: UiEvents {
    data class GetOrder(val orders: List<Order>): HomeUiEvents()
    data class ShowLoading(val display: Boolean) : HomeUiEvents()
    object DisplayFinishOrder: HomeUiEvents()
    object ClearAll: HomeUiEvents()
}
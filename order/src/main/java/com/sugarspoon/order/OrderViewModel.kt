package com.sugarspoon.order

import com.sugarspoon.commons.base.BaseViewModel
import com.sugarspoon.data.model.LemonadeType
import com.sugarspoon.data.model.Order
import javax.inject.Inject

class OrderViewModel @Inject constructor() :
    BaseViewModel<OrderUiStates, OrderUiEvents>(OrderUiStates()) {

    override fun handler(oldState: OrderUiStates, events: OrderUiEvents) {
        when (events) {
            is OrderUiEvents.Add200ml -> {
                val order = if (events.isChecked) {
                    oldState.order.add(events.order)
                    oldState.order
                } else {
                    oldState.order.remove(events.order)
                    oldState.order
                }
                val amount = order.map { it.price }.fold(0f) { acc, total ->
                    total.toFloat() + acc
                }
                createNewState(
                    newState = oldState.copy(
                        order = order,
                        smallLemonade = events.isChecked,
                        amount = amount
                    )
                )
            }

            is OrderUiEvents.Add300ml -> {
                val order = if (events.isChecked) {
                    oldState.order.add(events.order)
                    oldState.order
                } else {
                    oldState.order.remove(events.order)
                    oldState.order
                }
                val amount = order.map { it.price }.fold(0f) { acc, total ->
                    total.toFloat() + acc
                }
                createNewState(
                    newState = oldState.copy(
                        order = order,
                        mediumLemonade = events.isChecked,
                        amount = amount
                    )
                )
            }

            is OrderUiEvents.Add500ml -> {
                val order = if (events.isChecked) {
                    oldState.order.add(events.order)
                    oldState.order
                } else {
                    oldState.order.remove(events.order)
                    oldState.order
                }
                val amount = order.map { it.price }.fold(0f) { acc, total ->
                    total.toFloat() + acc
                }
                createNewState(
                    newState = oldState.copy(
                        order = order,
                        largeLemonade = events.isChecked,
                        amount = amount
                    )
                )
            }

            is OrderUiEvents.ClearAll -> {
                createNewState(newState = OrderUiStates())
            }
        }
    }

    fun clearAll() {
        emitEvent(OrderUiEvents.ClearAll)
    }

    fun onChoose200ml(isChecked: Boolean) {
        emitEvent(
            OrderUiEvents.Add200ml(
                order = Order(
                    price = "2.00",
                    type = LemonadeType.SMALL
                ),
                isChecked = isChecked
            )
        )
    }

    fun onChoose300ml(isChecked: Boolean) {
        emitEvent(
            OrderUiEvents.Add300ml(
                order = Order(
                    price = "3.00",
                    type = LemonadeType.MEDIUM
                ),
                isChecked = isChecked
            )
        )
    }

    fun onChoose500ml(isChecked: Boolean) {
        emitEvent(
            OrderUiEvents.Add500ml(
                order = Order(
                    price = "5.00",
                    type = LemonadeType.LARGE
                ),
                isChecked = isChecked
            )
        )
    }
}

data class OrderUiStates(
    val order: MutableList<Order> = mutableListOf(),
    val smallLemonade: Boolean = false,
    val mediumLemonade: Boolean = false,
    val largeLemonade: Boolean = false,
    val isLoading: Boolean = false,
    val amount: Float = 0f,
    val clearAll: Boolean = false
) : com.sugarspoon.commons.base.UiStates

sealed class OrderUiEvents : com.sugarspoon.commons.base.UiEvents {
    data class Add200ml(val order: Order, val isChecked: Boolean) : OrderUiEvents()
    data class Add300ml(val order: Order, val isChecked: Boolean) : OrderUiEvents()
    data class Add500ml(val order: Order, val isChecked: Boolean) : OrderUiEvents()
    object ClearAll: OrderUiEvents()
}





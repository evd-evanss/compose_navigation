package com.sugarspoon.data.model

import java.io.Serializable

data class Order(
    val price: String = "",
    val type: LemonadeType = LemonadeType.EMPTY
) : Serializable

enum class LemonadeType(val description: String) {
    SMALL("Lemonade 200 ml"),
    MEDIUM("Lemonade 300 ml"),
    LARGE("Lemonade 500 ml"),
    EMPTY(""),
}
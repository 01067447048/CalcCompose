package com.jaehyeon.compose.calccompose.main.state

import com.jaehyeon.compose.calccompose.main.events.CalculatorOperation

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)

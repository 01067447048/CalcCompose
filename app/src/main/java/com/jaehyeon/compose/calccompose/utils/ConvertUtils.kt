package com.jaehyeon.compose.calccompose.utils

import com.jaehyeon.compose.calccompose.main.events.CalculatorOperation
import com.jaehyeon.compose.calccompose.main.state.CalculatorState

object ConvertUtils {

    fun state2String(calculatorState: CalculatorState): String {
        if (calculatorState.operation != null && calculatorState.number1.isNotBlank() && calculatorState.number2.isNotBlank())
            return calculatorState.number1 + calculatorState.operation.symbol + calculatorState.number2
        throw Exception("CalculatorState Error")
    }

    fun string2State(result: String): CalculatorState {
        return when {
            result.contains("+") -> {
                val numbers = result.split("+")
                if (numbers.size != 2) throw Exception("Results Numbers Error")
                CalculatorState(
                    number1 = numbers[0],
                    number2 = numbers[1],
                    operation = CalculatorOperation.Add
                )
            }
            result.contains("-") -> {
                val numbers = result.split("-")
                if (numbers.size != 2) throw Exception("Results Numbers Error")
                CalculatorState(
                    number1 = numbers[0],
                    number2 = numbers[1],
                    operation = CalculatorOperation.Subtract
                )
            }
            result.contains("x") -> {
                val numbers = result.split("x")
                if (numbers.size != 2) throw Exception("Results Numbers Error")
                CalculatorState(
                    number1 = numbers[0],
                    number2 = numbers[1],
                    operation = CalculatorOperation.Multiply
                )
            }
            result.contains("/") -> {
                val numbers = result.split("+")
                if (numbers.size != 2) throw Exception("Results Numbers Error")
                CalculatorState(
                    number1 = numbers[0],
                    number2 = numbers[1],
                    operation = CalculatorOperation.Divide
                )
            }

            result == "" -> {
                CalculatorState()
            }

            else -> {
                throw Exception("Results Numbers Error")
            }
        }
    }
}
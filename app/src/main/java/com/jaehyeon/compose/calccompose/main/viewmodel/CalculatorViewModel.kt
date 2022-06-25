package com.jaehyeon.compose.calccompose.main.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jaehyeon.compose.calccompose.main.events.CalculatorActions
import com.jaehyeon.compose.calccompose.main.events.CalculatorOperation
import com.jaehyeon.compose.calccompose.main.state.CalculatorState
import com.jaehyeon.compose.calccompose.utils.ConvertUtils
import com.jaehyeon.compose.calccompose.utils.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Jaehyeon on 2022/06/23.
 */
@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val preference: SharedPreferenceHelper
): ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    init {
        restoreResult()
    }

    fun onAction(action: CalculatorActions) {
        when(action) {
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> state = CalculatorState()
            is CalculatorActions.Operation -> enterOperation(action.operation)
            is CalculatorActions.Calculator -> performCalculation()
            is CalculatorActions.Delete -> performDeletion()
        }
    }

    private fun performDeletion() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null

            )
            saveResult()
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }

        if (!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
            return
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if(state.number1.length >= MAX_NUM_LENGTH) {
                 return
            }

            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }

        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    private fun saveResult() {
        preference.result = ConvertUtils.state2String(state)
    }

    private fun restoreResult() {
        state = ConvertUtils.string2State(preference.result)
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}
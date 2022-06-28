package com.jaehyeon.compose.calccompose.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaehyeon.compose.calccompose.ui.composes.Calculator
import com.jaehyeon.compose.calccompose.main.viewmodel.CalculatorViewModel
import com.jaehyeon.compose.calccompose.ui.theme.CalcComposeTheme
import com.jaehyeon.compose.calccompose.ui.theme.MediumGray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcComposeTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp

                Calculator(
                    state = state,
                    onAction = viewModel::onAction,
                    buttonSpace = buttonSpacing,
                    modifier = Modifier.fillMaxSize()
                        .background(MediumGray)
                        .padding(16.dp)
                )

            }
        }
    }
}

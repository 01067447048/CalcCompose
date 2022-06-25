package com.jaehyeon.compose.calccompose.utils

import com.jaehyeon.compose.calccompose.main.state.CalculatorState

/**
 * Created by Jaehyeon on 2022/06/25.
 */
interface SharedPreferenceHelper {

    // 저장할 결과
    var result: String

    // 전체 삭제
    fun clear()
}
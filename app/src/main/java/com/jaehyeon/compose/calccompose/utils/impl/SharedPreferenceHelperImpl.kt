package com.jaehyeon.compose.calccompose.utils.impl

import android.content.Context
import androidx.core.content.edit
import com.jaehyeon.compose.calccompose.R
import com.jaehyeon.compose.calccompose.utils.SharedPreferenceHelper
import javax.inject.Inject

/**
 * Created by Jaehyeon on 2022/06/25.
 */
class SharedPreferenceHelperImpl @Inject constructor(
    context: Context
): SharedPreferenceHelper {

    private val sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    override var result: String
        get() = sharedPreferences.getString("result", "") ?: ""
        @Synchronized
        set(value) {
            sharedPreferences.edit(false) {
                putString("result", value)
            }
        }

    override fun clear() {
        sharedPreferences.edit {
            remove("result").apply()
        }
    }

}
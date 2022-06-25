package com.jaehyeon.compose.calccompose.di

import android.content.Context
import com.jaehyeon.compose.calccompose.utils.SharedPreferenceHelper
import com.jaehyeon.compose.calccompose.utils.impl.SharedPreferenceHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext
        context: Context
    ): SharedPreferenceHelper = SharedPreferenceHelperImpl(context)
}
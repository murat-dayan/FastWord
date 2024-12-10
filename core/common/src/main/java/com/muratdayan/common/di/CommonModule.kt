package com.muratdayan.common.di

import android.content.Context
import com.muratdayan.common.LoginStateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    fun provideLoginStateManager(@ApplicationContext context: Context): LoginStateManager {
        return LoginStateManager(context)
    }

}
package com.muratdayan.settings.di

import com.muratdayan.settings.data.repository.SettingsRepositoryImpl
import com.muratdayan.settings.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    fun provideSettingsRepository(
        supabaseClient: SupabaseClient
    ): SettingsRepository {
        return SettingsRepositoryImpl(supabaseClient)
    }
}
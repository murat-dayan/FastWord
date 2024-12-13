package com.muratdayan.game.di

import com.muratdayan.game.data.repository.GameRepositoryImpl
import com.muratdayan.game.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient

@Module
@InstallIn(SingletonComponent::class)
object GameModule {

    @Provides
    fun provideGameRepository(supabaseClient: SupabaseClient): GameRepository {
        return GameRepositoryImpl(supabaseClient)
    }

}
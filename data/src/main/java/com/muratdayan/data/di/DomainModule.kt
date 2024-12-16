package com.muratdayan.data.di

import com.muratdayan.data.repository.DomainRepositoryImpl
import com.muratdayan.domain.repository.DomainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideDomainRepository(supabaseClient: SupabaseClient): DomainRepository {
        return DomainRepositoryImpl(supabaseClient)
    }
}
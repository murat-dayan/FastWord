package com.muratdayan.data.di

import com.muratdayan.data.repository.FriendsDomainRepositoryImpl
import com.muratdayan.data.repository.UsersDomainRepositoryImpl
import com.muratdayan.domain.repository.FriendsDomainRepository
import com.muratdayan.domain.repository.UsersDomainRepository
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
    fun provideFriendsDomainRepository(supabaseClient: SupabaseClient): FriendsDomainRepository {
        return FriendsDomainRepositoryImpl(supabaseClient)
    }

    @Provides
    @Singleton
    fun provideUsersDomainRepository(supabaseClient: SupabaseClient): UsersDomainRepository {
        return UsersDomainRepositoryImpl(supabaseClient)
    }
}
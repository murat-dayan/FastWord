package com.muratdayan.data.di

import com.muratdayan.data.repository.FriendsDomainRepositoryImpl
import com.muratdayan.data.repository.UserDomainRepositoryImpl
import com.muratdayan.data.repository.UserStatsDomainRepositoryImpl
import com.muratdayan.domain.repository.FriendsDomainRepository
import com.muratdayan.domain.repository.UserDomainRepository
import com.muratdayan.domain.repository.UserStatsDomainRepository
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
    fun provideUserStatsDomainRepository(supabaseClient: SupabaseClient): UserStatsDomainRepository {
        return UserStatsDomainRepositoryImpl(supabaseClient)
    }

    @Provides
    @Singleton
    fun provideUserDomainRepository(supabaseClient: SupabaseClient): UserDomainRepository {
        return UserDomainRepositoryImpl(supabaseClient)
    }

}
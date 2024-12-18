package com.muratdayan.friends.di

import com.muratdayan.friends.data.repository.FriendsRepositoryImpl
import com.muratdayan.friends.domain.repository.FriendsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient

@Module
@InstallIn(SingletonComponent::class)
object FriendsModule {

    @Provides
    fun provideFriendsRepository(supabaseClient: SupabaseClient): FriendsRepository {
        return FriendsRepositoryImpl(supabaseClient)
    }

}
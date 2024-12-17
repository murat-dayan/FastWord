package com.muratdayan.profile.di

import com.muratdayan.profile.data.repository.ProfileRepositoryImpl
import com.muratdayan.profile.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {


    @Provides
    fun providesProfileRepository(supabaseClient: SupabaseClient): ProfileRepository {
        return ProfileRepositoryImpl(supabaseClient)
    }
}
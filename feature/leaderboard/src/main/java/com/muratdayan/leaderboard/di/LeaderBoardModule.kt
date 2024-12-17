package com.muratdayan.leaderboard.di

import com.muratdayan.leaderboard.data.repository.LeaderBoardRepositoryImpl
import com.muratdayan.leaderboard.domain.repository.LeaderBoardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LeaderBoardModule {


    @Provides
    fun provideLeaderBoardRepository(supabaseClient: SupabaseClient): LeaderBoardRepository {
        return LeaderBoardRepositoryImpl(supabaseClient)
    }

}
package com.muratdayan.settings.data.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.settings.domain.repository.SettingsRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
): SettingsRepository {
    override fun exitApp(): Flow<Result<Boolean, AppError>> = flow {

        try {
            supabaseClient.auth.signOut()
            emit(Result.Success(true))
        }catch (e:Exception){
            emit(Result.Error(DataError.Remote.ServerError))
        }

    }


}
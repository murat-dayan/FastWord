package com.muratdayan.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserStatsModel
import com.muratdayan.domain.repository.UsersDomainRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersDomainRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
): UsersDomainRepository {
    override fun getUserStats(): Flow<Result<UserStatsModel, AppError>> = flow {

        try {

            val user = supabaseClient.auth.currentUserOrNull()
            Log.d("GameRepositoryImpl", "getUserStats: $user")

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else{

                val response = supabaseClient
                    .from("user_stats")
                    .select {
                        filter {
                            eq("user_id",user.id)
                        }
                    }.decodeSingleOrNull<UserStatsModel>()

                response?.let {
                    it.getUpdatedAtAsInstant()
                    emit(Result.Success(it))
                }
            }


        }catch (e: Exception){
            Log.e("GameRepositoryImpl", "getUserStats: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }

    }


}
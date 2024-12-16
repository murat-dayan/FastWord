package com.muratdayan.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserStatsModel
import com.muratdayan.domain.repository.UserStatsDomainRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserStatsDomainRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
): UserStatsDomainRepository {
    override fun getUserStats(): Flow<Result<UserStatsModel, AppError>> = flow {

        try {
            val user = supabaseClient.auth.currentUserOrNull()
            Log.d("UserStatsDomainRepositoryImpl", "getUserStats: $user")

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
            Log.e("UserStatsDomainRepositoryImpl", "getUserStats: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }

    }

    override fun updateToken(tokenNewValue: Int): Flow<Result<Unit, AppError>> = flow {

        try {
            val user = supabaseClient.auth.currentUserOrNull()
            Log.d("UserStatsDomainRepositoryImpl", "updateToken: $user")

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else {
                supabaseClient
                    .from("user_stats")
                    .update({
                        set("token",tokenNewValue)
                    }){
                        filter {
                            eq("user_id",user.id)
                        }
                    }

                emit(Result.Success(Unit))

            }
        }catch (e: Exception){
            Log.e("UserStatsDomainRepositoryImpl", "updateToken: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

    override fun updateEnergy(energyNewValue: Int): Flow<Result<Unit, AppError>> = flow {

        try {
            val user = supabaseClient.auth.currentUserOrNull()
            Log.d("UserStatsDomainRepositoryImpl", "updateEnergy: $user")

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else {
                supabaseClient
                    .from("user_stats")
                    .update({
                        set("energy",energyNewValue)
                    }){
                        filter {
                            eq("user_id",user.id)
                        }
                    }

                emit(Result.Success(Unit))

            }
        }catch (e: Exception){
            Log.e("UserStatsDomainRepositoryImpl", "updateEnergy: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

    override fun updateEmerald(emeraldNewValue: Int): Flow<Result<Unit, AppError>> = flow {

        try {
            val user = supabaseClient.auth.currentUserOrNull()
            Log.d("UserStatsDomainRepositoryImpl", "updateEnergy: $user")

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else {
                supabaseClient
                    .from("user_stats")
                    .update({
                        set("emerald",emeraldNewValue)
                    }){
                        filter {
                            eq("user_id",user.id)
                        }
                    }

                emit(Result.Success(Unit))

            }
        }catch (e: Exception){
            Log.e("UserStatsDomainRepositoryImpl", "updateEnergy: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }


}
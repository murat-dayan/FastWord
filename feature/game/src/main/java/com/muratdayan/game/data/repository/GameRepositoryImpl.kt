package com.muratdayan.game.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.UserStatsModel
import com.muratdayan.game.domain.repository.GameRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : GameRepository{

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

    override fun getFriends(): Flow<Result<List<FriendsDataModel>, AppError>> = flow{
        try {
            val user = supabaseClient.auth.currentUserOrNull()

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else{
                val response = supabaseClient
                    .from("friends")
                    .select(Columns.raw("""
                        friend_id,
                        user:users!friend_id(id,user_name)
                    """.trimIndent())){
                        filter {
                            eq("user_id",user.id)
                        }
                    }


                Log.d("GameRepositoryImpl", "getFriends: $response")
                val decodeResponse = response.decodeList<FriendsDataModel>()

                if (decodeResponse.isEmpty()){
                    emit(Result.Error(DataError.Remote.ServerError))
                }else{
                    emit(Result.Success(decodeResponse))
                }
            }
        }catch (e:Exception){
            Log.e("GameRepositoryImpl", "getFriends: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }


}
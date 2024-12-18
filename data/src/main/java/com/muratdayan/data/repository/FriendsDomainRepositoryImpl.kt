package com.muratdayan.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.repository.FriendsDomainRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FriendsDomainRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : FriendsDomainRepository{
    override fun getFriends(): Flow<Result<List<FriendsDataModel>, com.muratdayan.common.AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else{
                val response = supabaseClient
                    .from("friends")
                    .select(
                        Columns.raw("""
                        friend_id,
                        user:users!friend_id(id,user_name),
                        status
                    """.trimIndent())){
                        filter {
                            eq("user_id",user.id)
                            eq("status","accepted")
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
            Log.e("FriendsDomainRepositoryImpl", "getFriends: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

    override fun updateFriendStatus(
        statusValue: String,
        friendId: String
    ): Flow<Result<Unit, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else{
                supabaseClient
                    .from("friends")
                    .update({
                        set("status",statusValue)
                    }){
                        filter {
                            eq("user_id",user.id)
                            eq("friend_id",friendId)
                        }
                    }

                emit(Result.Success(Unit))
            }
        }catch (e:Exception){
            Log.e("FriendsDomainRepositoryImpl", "getFriends: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

}
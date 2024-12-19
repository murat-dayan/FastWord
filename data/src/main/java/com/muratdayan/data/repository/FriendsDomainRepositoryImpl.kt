package com.muratdayan.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.FriendsViewModel
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

    override fun getFriends(): Flow<Result<List<FriendsViewModel>, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else{

                val response = supabaseClient
                    .from("friends_view")
                    .select (Columns.raw("*")){
                        filter {
                            eq("user_id",user.id)
                        }
                    }.decodeList<FriendsViewModel>()

                Log.d("FriendsDomainRepositoryImpl", "getFriends: $response")

                if (response.isEmpty()){
                    emit(Result.Error(DataError.Remote.ServerError))
                }else{
                    val friendsList = response.map { friend ->

                        if (friend.user_id == user.id) {
                            friend.copy(user_id = user.id, friend_id = friend.friend_id)
                        } else {
                            friend.copy(user_id = user.id, friend_id = friend.user_id)
                        }
                    }
                    emit(Result.Success(friendsList))
                }
            }
        }catch (e:Exception){
            Log.e("FriendsDomainRepositoryImpl", "getFriends: ${e.message}")
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }

    override fun updateFriendStatus(
        statusValue: String,
        senderId: String
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
                            eq("user_id",senderId)
                            eq("friend_id",user.id)
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
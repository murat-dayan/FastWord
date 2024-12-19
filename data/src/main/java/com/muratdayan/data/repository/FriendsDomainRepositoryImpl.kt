package com.muratdayan.data.repository

import android.util.Log
import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.mapper.toFriendsDataModel
import com.muratdayan.domain.model.AcceptedFriendsModel
import com.muratdayan.domain.model.FriendsDataModel
import com.muratdayan.domain.model.RequestedFriendsModel
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

    override fun getFriends(): Flow<Result<List<FriendsDataModel>, AppError>> = flow {
        try {
            val user = supabaseClient.auth.currentUserOrNull()

            if (user == null){
                emit(Result.Error(DataError.Remote.Unauthorized))
                return@flow
            }else{

                val requestedFriendsResponse = supabaseClient
                    .from("friends")
                    .select (
                        Columns.raw("""
                            friend_id,
                            user:users!friend_id(id,user_name,avatar_uri),
                            status
                        """.trimIndent())
                    ){
                        filter {
                            eq("user_id",user.id)
                            eq("status","accepted")
                        }
                    }.decodeList<RequestedFriendsModel>()

                val acceptedFriendsResponse = supabaseClient
                    .from("friends")
                    .select (
                        Columns.raw("""
                            user_id,
                            user:users!user_id(id,user_name,avatar_uri),
                            status
                        """.trimIndent())
                    ){
                        filter {
                            eq("friend_id",user.id)
                            eq("status","accepted")
                        }
                    }.decodeList<AcceptedFriendsModel>()

                val requestedFriends = requestedFriendsResponse.map { it.toFriendsDataModel() }
                val acceptedFriends = acceptedFriendsResponse.map { it.toFriendsDataModel() }

                val allFriends : List<FriendsDataModel> = requestedFriends + acceptedFriends

                if (allFriends.isEmpty()){
                    emit(Result.Success(emptyList()))
                }else{
                    emit(Result.Success(allFriends))
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